package com.run.agg

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._

object Aggregate {

  def process(spark: SparkSession, config: Config): Unit = {
    val df: DataFrame =
      spark.read
          .format("csv")
          .option("header", "true")
          .option("inferSchema", "true")
          .load(config.inputFilePath)

  }
}
