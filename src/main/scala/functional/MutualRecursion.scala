package functional


/**
  * Created by inakov on 03.11.16.
  */
object MutualRecursion extends App{

  def isOdd(n: Long): Boolean =
    if(n == 0) false else isEven(n - 1)


  def isEven(n: Long): Boolean =
    if(n == 0) true else isOdd(n - 1)

  println(isOdd(11))

  import scala.util.control.TailCalls._
  def isOddTrampoline(n: Long): TailRec[Boolean] =
    if(n == 0) done(false) else tailcall(isEvenTrampoline(n - 1))


  def isEvenTrampoline(n: Long): TailRec[Boolean] =
    if(n == 0) done(true) else tailcall(isOddTrampoline(n - 1))
}
