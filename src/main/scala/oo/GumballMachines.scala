package oo

import scala.util.Random

/**
  * Created by inakov on 09.01.17.
  *
  * Simple implementation of State Pattern
  */

trait State {
  def insertCoin(machine: Machine): Unit = println("Please wait, we are preparing your gumball.")
  def ejectCoin(machine: Machine): Unit = println("You haven't inserted a coin!")
  def turnCrank(machine: Machine): Unit
  def dispense(machine: Machine): Unit = println("No gumball dispensed.")
}

class NoCoin extends State{
  override def insertCoin(machine: Machine): Unit = {
    println("You inserted a coin ...")
    machine.currentState = Machine.hasCoinState
  }

  override def turnCrank(machine: Machine): Unit = println("You turned, buy there is no coin!")
}

class HasCoin extends State {
  val random = new Random()

  override def insertCoin(machine: Machine): Unit = println("There is no place for an other coin!")

  override def ejectCoin(machine: Machine): Unit = machine.currentState = Machine.noCoinState

  override def turnCrank(machine: Machine): Unit = {
    println("You turned...")
    val winning = random.nextInt(10) == 0
    if(winning) machine.currentState = Machine.winningState
    else machine.currentState = Machine.soldState
  }
}

class Sold extends State {
  override def ejectCoin(machine: Machine): Unit = println("Sorry, you already turned the crank. We don't support money back")

  override def turnCrank(machine: Machine): Unit = println("Turing twice doesn't get you another gumball. :)")

  override def dispense(machine: Machine): Unit = {
    machine.releaseBall()
    if(machine.gumballs > 0) machine.currentState = Machine.noCoinState
    else  machine.currentState = Machine.soldOutState
  }
}

class SoldOut extends State {
  override def insertCoin(machine: Machine): Unit = println("The machine is sold out!")

  override def turnCrank(machine: Machine): Unit = println("There is no gumballs.")
}

class Winning extends State {
  override def ejectCoin(machine: Machine): Unit = println("Sorry, you already turned the crank. We don't support money back")

  override def turnCrank(machine: Machine): Unit = println("Turing twice doesn't get you another gumball. :)")

  override def dispense(machine: Machine): Unit = {
    machine.releaseBall()
    if(machine.gumballs <= 0) machine.currentState = Machine.soldOutState
    else{
      machine.releaseBall()
      if(machine.gumballs > 0) machine.currentState = Machine.noCoinState
      else  machine.currentState = Machine.soldOutState
    }
  }
}

object Machine {
  val noCoinState: State = new NoCoin
  val hasCoinState: State = new HasCoin
  val soldState: State = new Sold
  val soldOutState: State = new SoldOut
  val winningState: State = new Winning

  def initState(): State = noCoinState
}

trait Machine {
  self =>
  var currentState: State
  var gumballs: Int

  def insertCoin(): Unit = currentState.insertCoin(self)
  def ejectCoin(): Unit = currentState.ejectCoin(self)
  def turnCrank(): Unit = {
    currentState.turnCrank(self)
    currentState.dispense(self)
  }
  def releaseBall(): Unit = {
    if(gumballs > 0){
      println("A gumball comes rolling out the slot ...")
      gumballs = gumballs - 1
    }
  }
}

class GumballMachine(initialGumballs: Int) extends Machine {
  override var gumballs: Int = initialGumballs
  override var currentState: State = Machine.initState()

  override def toString = s"GumballMachine($gumballs, $currentState)"
}

object GumballMachines extends App{
  val machine = new GumballMachine(5)

  println(machine)
  machine.insertCoin()
  machine.turnCrank()

  println(machine)
  machine.insertCoin()
  machine.turnCrank()
  machine.insertCoin()
  machine.turnCrank()

  println(machine)
}
