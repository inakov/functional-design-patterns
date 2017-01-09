package oo

import java.awt.MenuComponent

import scala.collection.mutable.ListBuffer

/**
  * Created by inakov on 09.01.17.
  *
  * Simple implementation of Composite Pattern
  */

trait MenuComponent {
  def add(menuComponent: MenuComponent): Unit = {
    throw new UnsupportedOperationException
  }
  def remove(menuComponent: MenuComponent): Unit = {
    throw new UnsupportedOperationException
  }
  def getChild(index: Int): MenuComponent = {
    throw new UnsupportedOperationException
  }
  def getName(): String = {
    throw new UnsupportedOperationException
  }
  def getDescription(): String = {
    throw new UnsupportedOperationException
  }
  def getPrice(): Double = {
    throw new UnsupportedOperationException
  }
  def isVegan(): Boolean = {
    throw new UnsupportedOperationException
  }
  def print(): Unit = {
    throw  new UnsupportedOperationException
  }
}

class MenuItem(name: String, description: String, price: Double, vegan: Boolean) extends MenuComponent{
  override def getName(): String = name

  override def getDescription(): String = description

  override def getPrice(): Double = price

  override def isVegan(): Boolean = vegan

  override def print(): Unit = {
    val text = s"\t$name ${if(vegan) "(v)" else ""}, price: $price"
    println(text)
    println(s"\t--- $description")
  }
}

class Menu(name: String, description: String) extends MenuComponent{
  private val menuComponents = ListBuffer.empty[MenuComponent]

  override def getName(): String = name

  override def getDescription(): String = description

  override def add(menuComponent: MenuComponent): Unit = menuComponents += menuComponent

  override def remove(menuComponent: MenuComponent): Unit = menuComponents -= menuComponent

  override def print(): Unit = {
    println(s"\n$name, $description")
    println("----------------------")
    menuComponents.foreach(_.print())
  }
}


class Waitress(allMenus: MenuComponent) {

  def printMenu(): Unit = allMenus.print()

}

object HipsterMenu extends App{

  val pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast")
  val dinerMenu = new Menu("DINER MENU", "Lunch")
  val cafeMenu = new Menu("CAFE MENU", "Dinner")
  val dessertMenu = new Menu("DESSERT MENU", "Dessert of course!")

  val allMenus = new Menu("ALL MENUS", "All menus combined")
  allMenus.add(pancakeHouseMenu)
  allMenus.add(dinerMenu)
  allMenus.add(cafeMenu)

  pancakeHouseMenu.add(new MenuItem(
    "K&B's Pancake Breakfast",
    "Pancakes with scrambled eggs and toast",
    2.99,
    false
  ))
  pancakeHouseMenu.add(new MenuItem(
    "Regular Pancake Breakfast",
    "Pancakes with fried eggs and sausage",
    2.99,
    false
  ))
  pancakeHouseMenu.add(new MenuItem(
    "Blueberry Pancakes",
    "Pancakes with fried eggs and sausage",
    3.99,
    true
  ))

  dinerMenu.add(new MenuItem("Vegetarian BLT",
    "Lettuce and tomatoes on whole wheat BLT",
    1.80,
    true))
  dinerMenu.add(new MenuItem("Hotdog",
    "An american hotdog with relish, onions and slice of sourdough bread",
    3.05,
    true))
  dinerMenu.add(new MenuItem("Pasta",
    "Spaghetti with Marinara Sauce, and a slice of sourdough bread",
    3.80,
    true))

  dinerMenu.add(dessertMenu)
  dessertMenu.add(new MenuItem(
    "Apple Pie",
    "Apple pie with a flakey crust, topped with vanilla icecream",
    1.99,
    true
  ))
  dessertMenu.add(new MenuItem(
    "Cheesecake",
    "Creamy New York cheesecake with chocolate graham crust",
    2.89,
    false
  ))

  cafeMenu.add(new MenuItem(
    "Veggie Burger and Air Fries",
    "Veggie burger on a whole wheat bun, lettuce, tomato and fries",
    4.89,
    true
  ))
  cafeMenu.add(new MenuItem(
    "Soup of the day",
    "A coup of the soup of the day with side salad",
    3.49,
    false
  ))

  val waitress = new Waitress(allMenus)
  waitress.printMenu()
}
