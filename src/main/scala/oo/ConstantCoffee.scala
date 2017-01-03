package oo

/**
  * Created by inakov on 02.11.16.
  *
  * Simple implementation of Decorator Pattern
  */

trait Beverage {
  protected val _description: String

  def description(): List[String] = List(_description)
  def cost(): Double
}

class Espresso extends Beverage {
  val _description: String = "Espresso"

  override def cost(): Double = 0.6
}

class HouseBlend extends Beverage {
  override protected val _description: String = "HouseBlend"

  override def cost(): Double = 1.2
}

class Decaf extends Beverage {
  override protected val _description: String = "Decaf"

  override def cost(): Double = 0.8
}

trait CondimentDecorator extends Beverage{
  val beverage: Beverage

  override def description(): List[String] = beverage.description ++ List(_description)
}

class Milk(val beverage: Beverage) extends CondimentDecorator {
  override protected val _description: String = "Milk"

  override def cost(): Double = beverage.cost() + 0.2
}

class Mocha(val beverage: Beverage) extends CondimentDecorator {
  override protected val _description: String = "Mocha"

  override def cost(): Double = beverage.cost() + 0.4
}

class Soy(val beverage: Beverage) extends CondimentDecorator {
  override protected val _description: String = "Soy"

  override def cost(): Double = beverage.cost() + 0.3
}

class Whip(val beverage: Beverage) extends CondimentDecorator {
  override protected val _description: String = "Whip"

  override def cost(): Double = beverage.cost() + 0.6
}


object ConstantCoffee extends App{

  val beverage = new Milk(new Espresso)
  println(s"${beverage.description().mkString(", ")} - cost: ${beverage.cost()} EUR")

  val beverage2 = new Whip(new Mocha(new Mocha(new HouseBlend)))
  println(s"${beverage2.description().mkString(", ")} - cost: ${beverage2.cost()} EUR")

  val beverage3 = new Whip(new Mocha(new Soy(new Decaf)))
  println(s"${beverage3.description().mkString(", ")} - cost: ${beverage3.cost()} EUR")
}
