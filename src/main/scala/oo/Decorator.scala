package oo

/**
  * Created by inakov on 02.11.16.
  */
object Decorator extends App{

  def add(a: Int, b: Int) = a + b
  def subtract(a: Int, b: Int) = a - b
  def multiply(a: Int, b: Int) = a * b
  def divide(a: Int, b: Int) = a / b

  def createLoggingFunction(calcFunc: (Int, Int) => Int) =
    (a: Int, b: Int) => {
      val result = calcFunc(a, b)
      println(s"Result is $result")
      result
    }

  val loggingAdd = createLoggingFunction(add)
  val loggingSubtract = createLoggingFunction(subtract)

  loggingAdd(1, 4)
  loggingSubtract(5, 10)
}
