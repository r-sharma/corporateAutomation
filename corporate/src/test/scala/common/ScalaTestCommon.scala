package common

import org.scalatest.FeatureSpec
import org.scalatest.{GivenWhenThen, BeforeAndAfterAll, BeforeAndAfterEach}

class ScalaTestCommon extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll with BeforeAndAfterEach{

  //does nothing but helps avoid importing the same set of classes in every Feature class

}
