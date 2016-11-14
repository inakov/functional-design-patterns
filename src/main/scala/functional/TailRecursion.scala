package functional

import scala.annotation.tailrec

/**
  * Created by inakov on 03.11.16.
  */
object TailRecursion extends App{

  def imperativeSum(until: Int): Int = {
    var sum = 0

    var currentNumber = 1
    while(currentNumber < until){
      sum += currentNumber
      currentNumber+=1
    }
    sum
  }

  def sumRecursive(until: Int): Int = {
    if(until == 0) 0
    else until + sumRecursive(until - 1)
  }

  //Uses single stack frame and cannot overflow.
  def sumTailRecursive(until: Int, currentSum: Long): Long = {
    if(until == 0) currentSum
    else sumTailRecursive(until - 1, currentSum + until)
  }
  println(sumTailRecursive(1000000, 0))

  case class Person(firstName: String, lastName: String)
  def makePeople(firstNames: Seq[String], lastNames: Seq[String]): List[Person] = {

    @tailrec
    def helper(firstNames: Seq[String], lastNames: Seq[String], people: List[Person]): List[Person] = {
      if(firstNames.isEmpty) people
      else{
        val person = Person(firstNames.head, lastNames.head)
        helper(firstNames.tail, lastNames.tail, person::people)
      }
    }
    helper(firstNames, lastNames, Nil)
  }


}
