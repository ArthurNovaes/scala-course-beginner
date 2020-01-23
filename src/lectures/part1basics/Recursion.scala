package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)

      result
    }

  println(factorial(10))
  // println(factorial(5000)) // ERROR StackOverflow

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci tail recursive
   */

  // 1.
  @tailrec
  def concatenateTailRec(name: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenateTailRec(name, n-1, name + accumulator)

  println(concatenateTailRec("hello", 3, ""))

  // 2.
  def isPrimeTailRec(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeUntil(t -1, n % t != 0 && isStillPrime)

    isPrimeUntil(n / 2, true)
  }

  println(isPrimeTailRec(2003))
  println(isPrimeTailRec(629))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibHelper(t: Int, last: Int, nextToLast: Int): Int =
      if (t >= n) last
      else fibHelper(t - 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibHelper(2, 1, 1)
  }

  println(fibonacci(8))
}
