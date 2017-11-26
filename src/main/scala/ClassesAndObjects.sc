
class MutableAccount(private var initialBalance: Double = 0.0) {

  def deposit(money: Double): Double = {
    this.initialBalance += money
    this.initialBalance
  }

  def withdraw(money: Double): Double = {
    if (this.initialBalance >= money) {
      this.initialBalance -= money
    }
    this.initialBalance
  }

  def balance = initialBalance

}


val account = new MutableAccount(1000)

account.balance

account.deposit(100)

account.withdraw(500)

account.withdraw(700)

object MutableBank {

  def createAccount = new MutableAccount(50)

}

val acc1 = MutableBank.createAccount
val acc2 = MutableBank.createAccount


case class Account(id: Int, balance: Double = 0.0) {

  def deposit(money: Double): Account = {
    Account(id, this.balance + money)
  }

  def withdraw(money: Double): Account = {
    if (this.balance >= money) {
      Account(id, this.balance - money)
    } else {
      this
    }
  }

}

object Bank {

  private val random = scala.util.Random

  def createAccount = Account(random.nextInt, 50)

}

val acc = Bank.createAccount

acc.balance

val accDeposit = acc.deposit(100)

val accWithdrawal = accDeposit.withdraw(100)

accWithdrawal.balance

Bank.createAccount.deposit(100).withdraw(100).balance