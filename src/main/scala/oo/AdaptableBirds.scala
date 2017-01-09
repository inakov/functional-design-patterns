package oo

/**
  * Created by inakov on 09.01.17.
  *
  * Simple implementation of AdapterPattern
  */

trait Duck {
  def quack(): Unit
  def fly(): Unit
}

class MallardDuck extends Duck {
  override def quack(): Unit = println("Quack")

  override def fly(): Unit = println("I'm flying!!!")
}

trait Turkey {
  def gabble(): Unit
  def fly(): Unit
}

class WildTurkey extends Turkey {
  override def gabble(): Unit = println("Gabble gabble Gab")

  override def fly(): Unit = println("Flap flap flap (Flying small distance)")
}

class TurkeyAdapter(turkey: Turkey) extends Duck {
  override def quack(): Unit = turkey.gabble()

  override def fly(): Unit = {
    for(i <- 1 to 3) turkey.fly()
  }
}

object AdaptableBirds extends App {
  val duck = new MallardDuck

  val turkey = new WildTurkey
  val turkeyAdapter = new TurkeyAdapter(turkey)

  println("The Turkey ...")
  turkey.gabble()
  turkey.fly()

  println("The Duck ...")
  duck.quack()
  duck.fly()

  println("The Turkey adapter ...")
  methodForDucks(turkeyAdapter)

  def methodForDucks(duck: Duck): Unit ={
    duck.quack()
    duck.fly()
  }
}
