package oo

/**
  * Created by inakov on 02.11.16.
  * Small implementation of Strategy Pattern in Scala.
  */

trait Weapon {
  def dealDamage(attackPower: Int, spellPower: Int): Unit
}

class DualBlades extends Weapon {
  override def dealDamage(attackPower: Int, spellPower: Int): Unit = {
    val mainBladeDamage = attackPower + attackPower * 0.8
    val offHandBladeDamage = attackPower + attackPower * 0.4
    println(s"Quickly deals ${mainBladeDamage} with the main hand weapon followed by $offHandBladeDamage from off-hand weapon.")
  }

  override def toString: String = "[DualBlades]"
}

class GreatSword extends Weapon {
  override def dealDamage(attackPower: Int, spellPower: Int): Unit = {
    val damage = attackPower + attackPower * 4.0
    println(s"Makes a massive slash and deals ${damage}!")
  }

  override def toString: String = "[GreatSword]"
}

class FireStaff extends Weapon {
  override def dealDamage(attackPower: Int, spellPower: Int): Unit = {
    val spellDamage = spellPower + spellPower * 6.0
    println(s"Casts a deadly fireball for $spellDamage")
  }

  override def toString: String = "[FireStaff]"
}

trait Character {
  val name: String
  val attackPower: Int
  val spellPower: Int
  protected var weapon: Weapon = _

  def equipWeapon(newWeapon: Weapon): Unit = {
    println(s"$name equips $newWeapon.")
    this.weapon = newWeapon
  }

  def attack(): Unit = {
    println(s"$name prepares himself to attack: ")
    weapon.dealDamage(attackPower, spellPower)
  }
}

class Warrior(val name: String, initWeapon: Weapon) extends Character{
  override val attackPower: Int = 100
  override val spellPower: Int = 3

  equipWeapon(initWeapon)
}
class Assassin(val name: String, initWeapon: Weapon) extends Character {
  override val attackPower: Int = 110
  override val spellPower: Int = 10

  equipWeapon(initWeapon)
}
class Wizard(val name: String, initWeapon: Weapon) extends Character {
  override val attackPower: Int = 5
  override val spellPower: Int = 200

  equipWeapon(initWeapon)
}


object KnightsLegacy extends App{
  println("Creating warrior named TheKing!")
  val warrior = new Warrior("TheKing", new GreatSword)
  warrior.attack
  println("Creating assassin named Shadow!")
  val assassin = new Assassin("Shadow", new DualBlades)
  assassin.attack

  println("Creating wizard named Medivh with dual blades ;)")
  val wiz = new Wizard("Medivh", new DualBlades)
  wiz.attack
  wiz.equipWeapon(new FireStaff)
  wiz.attack
}
