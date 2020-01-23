package lectures.part2oop

import java.sql

import playground.{Prince, Cinderella => Princess}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports  extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  //import the package
  val princess = new Princess

  // packages are in hierarchy
  // match folder structure.

  // package object
  sayHello
  println(SPEEP_OF_LIGHT)

  // imports
  val prince = new Prince

  // 1. use FQ names
  val d = new Date
  val sqlDate = new SqlDate(2018, 5, 4)
  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
