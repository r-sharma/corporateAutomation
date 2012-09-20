package Page

import common._
import org.openqa.selenium.{WebDriver, By}


class LoginPage (driver: WebDriver, url: String) {

  val commonfuncs = new CommonFuncs()

  def navigate(){
    driver.get(url)
  }

  def login(userEmail: String, pwd: String) : Boolean = {
    try{
      commonfuncs.WaitUntilElementPresent(30, driver, "//input[@id='UserEmail']")
      driver.findElement(By.id("UserEmail")).clear()
      driver.findElement(By.id("UserEmail")).sendKeys(userEmail)
      driver.findElement(By.id("UserPassword")).clear()
      driver.findElement(By.id("UserPassword")).sendKeys(pwd)
      driver.findElement(By.id("btn_login")).click()
      commonfuncs.WaitUntilElementPresent(30, driver, "//a[@id='signout_link']")
      return driver.findElements(By.id("signout_link")).size()>0
    }catch{
      case e: Exception => println("exception caught: " + e.getMessage())
      commonfuncs.CaptureScreenshot("login" + commonfuncs.CurrentTimestamp(), driver)
      return false
    }
  }

  def logout(){
    if (driver.findElements(By.id("signout_link")).size()>0)
      driver.findElement(By.id("signout_link")).click()
  }

  def close(){
    driver.close()
  }
}
