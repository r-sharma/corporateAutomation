import common._

class DBFetch extends ScalaTestCommon {

  val commonfuncs = new CommonFuncs()

  feature("Fetch data") {
    scenario("Connecting to sql", SqlConn) {
      given("A Sql DB")
      val conn = commonfuncs.DBConnection(commonfuncs.ReadConf("userDB"),commonfuncs.ReadConf("passwordDB"),commonfuncs.ReadConf("datasource"),commonfuncs.ReadConf("mysqlDB"))

      when("I try to fetch")
      val data = commonfuncs.FetchingData(conn , "SELECT *  FROM db LIMIT 1")

      then("Data Fetched")
      while (data.next()) println(data.getString(2))
    }
  }
}