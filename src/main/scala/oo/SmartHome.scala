package oo

/**
  * Created by inakov on 09.01.17.
  *
  * Simple implementation of Command Pattern
  */

class Light(name: String) {
  def on(): Unit = println(s"$name light is ON!")
  def of(): Unit = println(s"$name light is OFF!")
}

class GarageDoor {
  def open(): Unit = println("Garage door is opened.")
  def close(): Unit = println("Garage door is closed.")
}

class Stereo {
  def on(): Unit = println("Stereo is on")
  def off(): Unit = println("Stereo is off")
  def setCD() = println("Stereo is set for CD")
  def setVolume(volume: Int) = println(s"Stereo volume set to $volume")
  def setRadio() = println("Stereo is set to radio.")
}

trait Command{
  def execute(): Unit
}

object NoCommand extends Command {
  override def execute(): Unit = {}
}

class LightOnCommand(light: Light) extends Command {
  override def execute(): Unit = {
    light.on()
  }
}

class LightOffCommand(light: Light) extends Command {
  override def execute(): Unit = {
    light.of()
  }
}

class OpenGarageDoorCommand(garageDoor: GarageDoor) extends Command {
  override def execute(): Unit = {
    garageDoor.open()
  }
}

class CloseGarageDoorCommand(garageDoor: GarageDoor) extends Command {
  override def execute(): Unit = {
    garageDoor.close()
  }
}

class PlayMyFavoriteCDCommand(stereo: Stereo) extends Command{
  override def execute(): Unit = {
    stereo.on()
    stereo.setCD()
    stereo.setVolume(7)
  }
}

class RemoteControl {

  val onCommands: Array[Command] = Array.fill(7)(NoCommand)
  val offCommands: Array[Command] = Array.fill(7)(NoCommand)

  def addCommand(slot: Int, onCommand: Command, offCommand: Command): Unit = {
    onCommands(slot) = onCommand
    offCommands(slot) = offCommand
  }

  def onButtonPushed(slot: Int): Unit = {
    onCommands(slot).execute()
  }

  def offButtonPushed(slot: Int): Unit = {
      offCommands(slot).execute()
  }


  override def toString = {
    val builder = new StringBuilder("\n--------- Remote Control --------\n")
    for(i <- onCommands.indices){
      builder.append(s"[slot $i] ${onCommands(i).getClass.getSimpleName} - ${offCommands(i).getClass.getSimpleName}\n")
    }

    builder.toString()
  }
}

object SmartHome extends App{
  val light = new Light("Living Room")
  val garageDoor = new GarageDoor
  val stereo = new Stereo

  val livingRoomLightOn = new LightOnCommand(light)
  val livingRoomLightOff = new LightOffCommand(light)

  val openGarage = new OpenGarageDoorCommand(garageDoor)
  val closeGarage = new CloseGarageDoorCommand(garageDoor)

  val partyMode = new PlayMyFavoriteCDCommand(stereo)

  val remoteControl = new RemoteControl
  remoteControl.addCommand(0, livingRoomLightOn, livingRoomLightOff)
  remoteControl.addCommand(1, openGarage, closeGarage)
  remoteControl.addCommand(2, partyMode, NoCommand)

  println(remoteControl)

  remoteControl.onButtonPushed(1)
  remoteControl.offButtonPushed(1)
  remoteControl.onButtonPushed(0)
  remoteControl.onButtonPushed(2)
}
