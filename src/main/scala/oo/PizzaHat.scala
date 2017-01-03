package oo

/**
  * Created by inakov on 03.01.17.
  *
  * Simple implementation of Abstract Factory Pattern
  */

// PizzaStore{create and order pizza} NYStylePizzaStore, ChicagoStylePizzaStore, Dough, Sauce, Cheese, Clams
// ThickCrustDough, ThinCrustDough, PlumTomatoSauce, MarinaraSauce, MozzarellaCheese, ReggianoCheese,
// FrozenClams, FreshClams, PizzaIngredientFactory, NYFactory, ChicagoFactory, Pepperoni/SlicedPepperoni,
// Veggies, Garlic, Onion, Mushroom, RedPepper, BlackOlives,
// Pizza{name, dough, sauce, veggies, cheese, pepperoni, clams, bake, cut, box, prepare()},
// CheesePizza, VeggiePizza, ClamPizza, PepperoniPizza

trait Pizza {
  val name: String

  var dough: Dough
  var sauce: Sauce
  var veggies: List[Veggie]
  var cheese: Cheese
  var pepperoni: Pepperoni
  var clams: Clams

  def prepare(): Unit

  def bake(): Unit = println("Bake for 25 minutes at 360'.")
  def cut(): Unit = println("Cut the pizza into diagonal slices.")
  def box(): Unit = println("Place pizza in brand box.")

}

trait Dough
class ThickCrustDough extends Dough{
  override def toString = "[ThickCrustDough]"
}

class ThinCrustDough extends Dough {
  override def toString = "[ThinCrustDough]"
}

trait Sauce
class PlumTomatoSauce extends Sauce{
  override def toString = "[PlumTomatoSauce]"
}

class MarinaraSauce extends Sauce{
  override def toString: String = "[MarinaraSauce]"
}

trait Veggie
class Garlic extends Veggie {
  override def toString: String = "[Garlic]"
}

class BlackOlives extends Veggie {
  override def toString: String = "[BlackOlives]"
}

class Onion extends Veggie {
  override def toString: String = "[Onion]"
}

class Mushroom extends Veggie {
  override def toString: String = "[Mushroom]"
}

class RedPepper extends Veggie {
  override def toString: String = "[RedPepper]"
}

trait Cheese
class ReggianoCheese extends Cheese {
  override def toString: String = "[ReggianoCheese]"
}

class MozzarellaCheese extends Cheese {
  override def toString: String = "[MozzarellaCheese]"
}

trait Pepperoni
class SlicedPepperoni extends Pepperoni {
  override def toString: String = "[SlicedPepperoni]"
}

trait Clams
class FreshClams extends Clams {
  override def toString: String = "[FreshClams]"
}

class FrozenClams extends Clams {
  override def toString: String = "[FrozenClams]"
}

class CheesePizza(override val name: String, val ingredientFactory: PizzaIngredientFactory) extends Pizza {
  override var dough: Dough = _
  override var sauce: Sauce = _
  override var veggies: List[Veggie] = _
  override var cheese: Cheese = _
  override var pepperoni: Pepperoni = _
  override var clams: Clams = _

  override def prepare(): Unit = {
    println(s"Preparing $name")
    dough = ingredientFactory.createDough()
    sauce = ingredientFactory.createSauce()
    cheese = ingredientFactory.createCheese()
  }
}

class ClamPizza(override val name: String, ingredientFactory: PizzaIngredientFactory) extends Pizza {
  override var dough: Dough = _
  override var sauce: Sauce = _
  override var veggies: List[Veggie] = _
  override var cheese: Cheese = _
  override var pepperoni: Pepperoni = _
  override var clams: Clams = _

  override def prepare(): Unit = {
    println(s"Preparing $name")
    dough = ingredientFactory.createDough()
    sauce = ingredientFactory.createSauce()
    cheese = ingredientFactory.createCheese()
    clams = ingredientFactory.createClams()
  }
}

class VeggiePizza(val name: String, ingredientFactory: PizzaIngredientFactory) extends Pizza {
  override var dough: Dough = _
  override var sauce: Sauce = _
  override var veggies: List[Veggie] = _
  override var cheese: Cheese = _
  override var pepperoni: Pepperoni = _
  override var clams: Clams = _

  override def prepare(): Unit = {
    println(s"Preparing $name")
    dough = ingredientFactory.createDough()
    sauce = ingredientFactory.createSauce()
    veggies = ingredientFactory.createVeggies()
  }
}

class PepperoniPizza(val name: String, ingredientFactory: PizzaIngredientFactory) extends Pizza {
  override var dough: Dough = _
  override var sauce: Sauce = _
  override var veggies: List[Veggie] = _
  override var cheese: Cheese = _
  override var pepperoni: Pepperoni = _
  override var clams: Clams = _

  override def prepare(): Unit = {
    println(s"Preparing $name")
    dough = ingredientFactory.createDough()
    sauce = ingredientFactory.createSauce()
    cheese = ingredientFactory.createCheese()
    pepperoni = ingredientFactory.createPepperoni()
  }
}

trait PizzaIngredientFactory {
  def createDough(): Dough
  def createSauce(): Sauce
  def createVeggies(): List[Veggie]
  def createCheese(): Cheese
  def createClams(): Clams
  def createPepperoni(): Pepperoni = new SlicedPepperoni
}

class NYPizzaFactory extends PizzaIngredientFactory {
  override def createDough(): Dough = new ThinCrustDough

  override def createSauce(): Sauce = new MarinaraSauce

  override def createVeggies(): List[Veggie] = List(new Garlic, new Onion, new Mushroom, new RedPepper)

  override def createCheese(): Cheese = new ReggianoCheese

  override def createClams(): Clams = new FreshClams
}

class ChicagoPizzaFactory extends PizzaIngredientFactory {
  override def createDough(): Dough = new ThickCrustDough

  override def createSauce(): Sauce = new PlumTomatoSauce

  override def createVeggies(): List[Veggie] = List(new Garlic, new BlackOlives, new Mushroom, new RedPepper)

  override def createCheese(): Cheese = new MozzarellaCheese

  override def createClams(): Clams = new FrozenClams
}

trait PizzaStore {
  def createPizza(pizzaType: String): Pizza

  def orderPizza(pizzaType: String): Pizza = {
   val pizza = createPizza(pizzaType)

    pizza.prepare()
    pizza.bake()
    pizza.cut()
    pizza.box()

    pizza
  }
}

class NYPizzaStore extends PizzaStore {
  val ingredientFactory = new NYPizzaFactory

  override def createPizza(pizzaType: String): Pizza = pizzaType match {
    case "cheese" => new CheesePizza("NY Style Cheese Pizza", ingredientFactory)
    case "veggie" => new VeggiePizza("NY Style Veggie Pizza", ingredientFactory)
    case "clam" => new ClamPizza("NY Style Clam Pizza", ingredientFactory)
    case "pepperoni" => new PepperoniPizza("NY Style Pepperoni Pizza", ingredientFactory)
  }
}

class ChicagoPizzaStore extends PizzaStore {
  val ingredientFactory = new ChicagoPizzaFactory

  override def createPizza(pizzaType: String): Pizza = pizzaType match {
    case "cheese" => new CheesePizza("Chicago Cheese Pizza", ingredientFactory)
    case "veggie" => new VeggiePizza("Chicago Veggie Pizza", ingredientFactory)
    case "clam" => new ClamPizza("Chicago Clam Pizza", ingredientFactory)
    case "pepperoni" => new PepperoniPizza("Chicago Pepperoni Pizza", ingredientFactory)
  }
}

object PizzaHat extends App{
  val nyStore = new NYPizzaStore
  val chicagoStore = new ChicagoPizzaStore

  val goshoOrder = nyStore.orderPizza("cheese")
  println(s"Gosho ordered a ${goshoOrder.name}\n\n")

  val ivanOrder = chicagoStore.orderPizza("pepperoni")
  println(s"Ivan ordered a ${ivanOrder.name}")
}

