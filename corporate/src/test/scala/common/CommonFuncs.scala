package common

import xml.XML
import org.apache.commons.io.FileUtils
import java.io.File
import org.openqa.selenium.support.ui.{ExpectedCondition, WebDriverWait}
import org.scalatest.Tag
import org.openqa.selenium._
import org.openqa.selenium.firefox.FirefoxDriver
import org.joda.time.DateTime
import java.sql.{ResultSet, DriverManager, Connection}

object FirstTag extends Tag("FirstTag")
object SqlConn extends Tag("SqlConn")

class CommonFuncs {

  def LaunchBrowser() : WebDriver = {
    val driver = new FirefoxDriver()
    driver
  }

  def ReadConf(node: String): String = {
    println()
    val dom = XML.loadFile("src/test/scala/config/config-qa.xml")
    var nodeValue = (dom \\ node).text
    if (System.getProperty(node) != null) nodeValue = System.getProperty(node)
    nodeValue
  }

  def CaptureScreenshot(fileName: String, driver: WebDriver) {
    FileUtils.copyFile(
      (driver.asInstanceOf[TakesScreenshot]).getScreenshotAs(OutputType.FILE),
      new File("ScreenCaptures/" + fileName + ".png")
    )
  }

  def WaitUntilElementPresent(timeout: Int, driver: WebDriver, xpath: String){
    val wait = new WebDriverWait(driver, timeout)
    wait.until(new ExpectedCondition[WebElement] {
      def apply(d: WebDriver) = d.findElement(By.xpath(xpath))
    })
  }

  def CurrentTimestamp() : String = {
    val timenow = DateTime.now()
    return (timenow.getDayOfMonth.toString + timenow.getMonthOfYear.toString +
            timenow.getYear.toString + timenow.getHourOfDay.toString +
            timenow.getMinuteOfDay.toString + timenow.getSecondOfDay.toString)
  }

  def CloseBrowser(driver: WebDriver){
    driver.close()

  }

  def DBConnection(username : String, password : String, database : String, dbLocation : String ) : Connection = {
    val conn_str = "jdbc:mysql://"+dbLocation+"/"+database+"?user="+username+"&password="+password
    //classOf[com.mysql.jdbc.Driver]
    val conn = DriverManager.getConnection(conn_str)
    conn
  }

  def FetchingData(conn : Connection, query : String ) : ResultSet = {
    val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
    val rs = statement.executeQuery(query)
    rs
  }

}








/*
      var myInt : Int = 0
      while(myInt < 30 && !(driver.findElements(By.id("UserEmail")).size()>0)) {
        myInt += 1
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
      }
      */