import com.run.agg.Config
import org.scalatest.funspec.AnyFunSpec


class ConfigTests extends AnyFunSpec {
  describe("A Stack") {
    it("Arguments and options should be passed correctly") {

      val args = Array[String](
        "-a",
        "runagg",
        "-d",
        "2020-01-20",
        "-i",
        "finename.csv"
      )

      val config = Config.parser.parse(args, Config())
      assert(!Some(config).contains(Some(None)))
    }

    it("Arguments and options should be passed correctly 2") {

      val args = Array[String](
        "--app_name",
        "runagg",
        "--date",
        "2020-01-20",
        "--input",
        "finename.csv"
      )

      val config = Config.parser.parse(args, Config())
      assert(!Some(config).contains(Some(None)))
    }

    it("Arguments and options should be passed correctly 3") {
      val args = Array[String](
        "--date",
        "2020-01-20",
        "--input",
        "finename.csv"
      )

      val config = Config.parser.parse(args, Config())
      assert(!Some(config).contains(Some(None)))
    }

    it("Test that date is a requirement") {

      val args = Array[String](
        "--input",
        "finename.csv"
      )

      val config = Config.parser.parse(args, Config())
      assert(Some(config) == Some(None))
    }
  }

}

