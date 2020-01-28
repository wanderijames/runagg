package com.run.agg

import org.apache.spark.sql.SparkSession

import scala.util.Properties

object Main extends App {
  val version = "0.1"

  Config.parser.parse(args, Config()) match {
    case Some(config) =>
      // Received correct arguments
      val sparkMasterUrl: String = Properties.envOrElse("SPARK_MASTER", "local[*]")

      val spark = SparkSession
        .builder()
        .config("spark.master", sparkMasterUrl)
        .appName(config.appName)
        .getOrCreate()

      Aggregate.process(spark, config)

    case _ =>
      // Received incorrect arguments
      // scopt will print the instructions on how to use the application
      println("")
  }

}
