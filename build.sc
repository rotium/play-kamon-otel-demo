import mill._
import $ivy.`com.lihaoyi::mill-contrib-playlib:`,  mill.playlib._

object playkamonoteldemo extends PlayModule with SingleModule {

  def scalaVersion = "2.13.12"
  def playVersion = "2.9.1"
  def twirlVersion = "1.5.1"

  object test extends PlayTests
}
