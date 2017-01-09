package oo

import scala.io.StdIn

/**
  * Created by inakov on 09.01.17.
  *
  * Simple implementation of Template Method Pattern
  *
  */


trait BeverageRecipe {

  final def prepareRecipe(): Unit = {
    boilWater()
    brew()
    pourInCup()
    if(customerWantsCondiments()){
      addCondiments()
    }
  }

  def brew(): Unit
  def addCondiments(): Unit

  def boilWater(): Unit = {
    println("Boiling water...")
  }

  def pourInCup(): Unit = {
    println(" Pouring into cup...")
  }

  def customerWantsCondiments(): Boolean = true
}

class CoffeeRecipe extends BeverageRecipe {
  override def brew(): Unit = println("Add Nesscoffee 3in1...")

  override def addCondiments(): Unit = {
    println("Adding more sugar...")
  }

  def getUserInput(): String = {
    println("Would you like more sugar with your coffee (y/n)?")
    StdIn.readLine()
  }

  override def customerWantsCondiments(): Boolean = {
    val answer = getUserInput()

    answer.toLowerCase() == "y"
  }
}

class TeaRecipe extends BeverageRecipe {
  override def brew(): Unit = {
    println("Drop a bag of tea")
  }

  override def addCondiments(): Unit = {
    println("Adding sugar")
    println("Adding lemon")
  }
}

object HotBeverage extends App{

  val tea = new TeaRecipe
  val coffee = new CoffeeRecipe

  println("Making tea ...")
  tea.prepareRecipe()

  println("\nMaking coffee ...")
  coffee.prepareRecipe()
}
