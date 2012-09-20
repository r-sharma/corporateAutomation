import common._
import org.openqa.selenium.WebDriver
import Page.LoginPage


class Login extends ScalaTestCommon {

  val commonfuncs = new CommonFuncs()
  val driver: WebDriver = commonfuncs.LaunchBrowser()
  val loginpage = new LoginPage(driver, commonfuncs.ReadConf("platformUrl"))

  override def beforeAll() {
    loginpage.navigate()
  }

  override def beforeEach() {
    //nothing to do
  }

  feature("Login to the platform") {
    info("As a valid user")
    info("I should be able to login to the application successfully")

    scenario("Login with valid credentials", FirstTag) {
      given("I am on login page")
      when("I enter valid user credentials and click login")
      then("I should be logged in successfully")
      assert(loginpage.login(commonfuncs.ReadConf("userEmail"), commonfuncs.ReadConf("userPwd")))
    }

    scenario("Login with invalid credentials") {
        given("I am on login page")
        when("I enter invalid user credentials and click login")
        then("I should not be logged in")
        assert(loginpage.login(commonfuncs.ReadConf("userEmail"), "inavlidPwd"))
    }
  }

  override def afterEach() {
    loginpage.logout()
  }

  override def afterAll() {
    commonfuncs.CloseBrowser(driver)
  }

}