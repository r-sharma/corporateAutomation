package Page

import common._
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.{WebDriver, By}


class HomePage(driver: WebDriver) {


  def navigate() {
    driver.get("/")
  }

}
