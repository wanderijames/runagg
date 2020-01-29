package com.run.agg

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._

import scala.util.Properties

object Aggregate {

  def process(spark: SparkSession, config: Config): Unit = {

    val joinMap = udf { values: Seq[Map[String, String]] =>  values.flatten.toMap }


    var df: DataFrame =
      spark.read
          .format("csv")
          .option("header", "true")
          .option("inferSchema", "true")
          .load(config.inputFilePath)
    df = df.withColumn("date", to_date(from_unixtime(col("timestamp"))).as("date"))
    df = df.filter(df("date") === config.aggDate)

    val df2: DataFrame = df
        .groupBy("identifier", "action")
        .agg(max("timestamp").as("high_timestamp"))

    val df_all: DataFrame = df.join(df2, Seq("identifier", "action"))

    val df_map: DataFrame = df_all
      .filter("timestamp == high_timestamp")
      .select("identifier", "action", "category", "date")
      .groupBy("identifier", "date").agg(collect_list(map(col("action"), col("category"))).as("map"))
      .withColumn("map", joinMap(col("map"))).withColumn("map", to_json(col("map")))

    // persistToHive(df_map)
    df_map.show(false)

    // If you need to write to a JDBC compliant database, uncomment the following
    // persistTojJdbcDB(df_map)
  }

  def persistTojJdbcDB(df: DataFrame): Unit = {
    // Get connection details from environment
    // Redshift table will have been partitioned by date
    val jdbcUrl: String = Properties.envOrElse(
      "EGRESS_JDBC_URL", "None")

    df.write.format("jdbc").option("url", jdbcUrl)
  }

  def persistToHive(df: DataFrame): Unit = {
    persistToHive(df, false)
  }

  def persistToHive(df: DataFrame, append: Boolean): Unit = {
    val tableName: String = Properties.envOrElse(
      "EGRESS_HIVE_TABLE_NAME", "New Table")

    if (append) {
      df.write.partitionBy("date").saveAsTable(tableName)
    } else {
      df.write.partitionBy("date").insertInto(tableName)
    }
  }
}
