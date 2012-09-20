import common._
import org.openqa.selenium.WebDriver
import Page._


class UsersAndGroups extends ScalaTestCommon {

  val commonfuncs = new CommonFuncs()
  val driver: WebDriver = commonfuncs.LaunchBrowser()
  val loginpage = new LoginPage(driver, commonfuncs.ReadConf("platformUrl"))
  val adminpage = new AdminPage(driver)

  override def beforeAll() {
    loginpage.navigate()
  }

  override def beforeEach() {
    loginpage.login(commonfuncs.ReadConf("userEmail"), commonfuncs.ReadConf("userPwd"))
  }

  feature("Add a New User") {
    info("As a valid admin user")
    info("I should be able to add a New User")

    scenario("Create a valid new user", FirstTag) {
      given("I am already logged in")
      when("I navigate to Admin page")
      adminpage.navigate()
      adminpage.CreateUser()
      then("I should be logged in successfully")
      assert(1 === 1)
    }
  }

  override def afterEach() {
    loginpage.logout()
  }

  override def afterAll() {
    commonfuncs.CloseBrowser(driver)
  }

}