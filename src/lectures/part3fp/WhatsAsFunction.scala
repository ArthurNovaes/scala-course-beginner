package lectures.part3fp

object WhatsAsFunction extends App {

  // DREAM: use functions as first class elements
  // PROBLEM: OOP

  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(stringToIntConverter("3") + 4)

  /*
    1. Define a function which takes 2 string and concatenates them
    2. transform the MyPredicate ahd MyTransformer into function types
    3. define a function which takes an int and returns another function which takes a int and returns an int
        - what's the type of this function
        - how to do it
   */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  println(concatenator("Hello ", "Scala"))

  // Function1[Int, Function1[Int, Int]]
  val superAdder: Int => (Int => Int) = (x: Int) => new Function[Int, Int] {
    override def apply(y: Int): Int = x + y
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}
