package Page

import common._
import org.openqa.selenium.{WebDriver, By}


class LoginPage (driver: WebDriver, url: String) {

  val commonfuncs = new CommonFuncs()
  val email_text_field = "//input[@id='UserEmail']"
  val pwd_text_field = "UserPassword"
  val login_button = "btn_login"
  val signout_link = "//a[@id='signout_link']"

  def navigate(){
    driver.get(url)
  }

  def login(userEmail: String, pwd: String) : Boolean = {
    try{
      commonfuncs.WaitUntilElementPresent(30, driver, email_text_field)
      //driver.findElement(By.id("UserEmail")).clear()
      driver.findElement(By.xpath(email_text_field)).clear()
      //driver.findElement(By.id("UserEmail")).sendKeys(userEmail)
      driver.findElement(By.xpath(email_text_field)).sendKeys(userEmail)
      driver.findElement(By.id(pwd_text_field)).clear()
      driver.findElement(By.id(pwd_text_field)).sendKeys(pwd)
      driver.findElement(By.id(login_button)).click()
      commonfuncs.WaitUntilElementPresent(30, driver, signout_link)
      return driver.findElements(By.xpath(signout_link)).size()>0
    }catch{
      case e: Exception => println("exception caught: " + e.getMessage())
      commonfuncs.CaptureScreenshot("login" + commonfuncs.CurrentTimestamp(), driver)
      return false
    }
  }

  def logout(){
    if (driver.findElements(By.xpath(signout_link)).size()>0)
      driver.findElement(By.xpath(signout_link)).click()
  }

  def close(){
    driver.close()
  }
}
