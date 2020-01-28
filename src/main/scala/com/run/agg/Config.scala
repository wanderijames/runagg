package com.run.agg


case class Config(
                 appName: String="RunAgg",
                 aggDate: String="",
                 inputFilePath: String=""
                 )

object Config {

  def parser = new scopt.OptionParser[Config]("Run Aggregate") {
    head("Run Aggregate", Main.version)

    opt[String]('a', "app_name").optional().valueName("Application Name").
      action((x, c) => c.copy(appName = x)).
      text("Application name registered in spark applications")

    opt[String]('d', "date").required().valueName("Date for Aggregation").
      action((x, c) => c.copy(aggDate = x)).
      text("Pull data for this date for aggregation")

    opt[String]('i', "input").required().valueName("Input File").
      action((x, c) => c.copy(inputFilePath = x)).
      text("CSV file path with input data")
  }

}
