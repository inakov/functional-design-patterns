package oo

/**
  * Created by inakov on 01.11.16.
  */
object Command extends App{

  class CashRegister(var total: Int){
    def addCash(toAdd: Int): Int = {
      total += toAdd
      total
    }
  }

  def makePurchase(register: CashRegister, amount: Int) = {
    val purchase: () => Unit  = () => {
      println(s"Executed purchase with amount $amount")
      register.addCash(amount)
    }

    purchase
  }

  var purchases: List[() => Unit] = List.empty
  def executePurchase(purchase: () => Unit) = {
    purchases = purchase :: purchases
    purchase()
  }

  val register = new CashRegister(0)
  val bussTicket = makePurchase(register, 10)

  executePurchase(bussTicket)

}
