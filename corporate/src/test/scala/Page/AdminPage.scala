package Page

import common._
import org.openqa.selenium.{WebDriver, By}

class AdminPage(driver: WebDriver) {

  val commonfuncs = new CommonFuncs()

  def navigate() {
    driver.findElement(By.linkText("Admin")).click()
    commonfuncs.WaitUntilElementPresent(120, driver, "//a[@id='new-user']")
  }

  def CreateUser() : Boolean = {
    try{
      val user = "user_" + commonfuncs.CurrentTimestamp()
      driver.findElement(By.id("new-user")).click()
      driver.findElement(By.id("email")).clear()
      driver.findElement(By.id("email")).sendKeys(user)
      driver.findElement(By.xpath("//div[@id='user-reveal']/div[4]/div/div/div[2]/div")).click()
      driver.findElement(By.id("first-name")).clear()
      driver.findElement(By.id("first-name")).sendKeys(user)
      driver.findElement(By.id("last-name")).clear()
      driver.findElement(By.id("last-name")).sendKeys("Testing")
      driver.findElement(By.id("phone")).clear()
      driver.findElement(By.id("phone")).sendKeys("8888888888")
      driver.findElement(By.id("twentyfour-hour")).click()
      driver.findElement(By.id("checkSendActivationEmail")).click()
      driver.findElement(By.id("group-save")).click()
      driver.findElement(By.id("filter-users-groups")).clear()
      driver.findElement(By.id("filter-users-groups")).sendKeys("nothin@testing.com")
      driver.findElement(By.id("filter-users-groups")).submit()
      return true
      //assert()
    }catch{
      case e: Exception => println("exception caught: " + e.getMessage())
      commonfuncs.CaptureScreenshot("Admin" + commonfuncs.CurrentTimestamp(), driver)
      return false
    }
  }
}
