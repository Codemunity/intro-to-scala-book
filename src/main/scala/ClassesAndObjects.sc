
// This is how constructors work in Scala, we defined a private variable
// with a default value
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

  // A simple version of a getter, the types are inferred
  def balance = initialBalance

}

// We can create instances of our `MutableAccount` class using the `new` keyword
val account = new MutableAccount(1000)

// Even though `balance` is a method, we don't have to use parenthesis
// because it doesn't take any parameters
account.balance

account.deposit(100)

account.withdraw(500)

// The result is the same as before because there are not enough funds,
// just something we chose when implementing our `MutableAccount`
account.withdraw(700)

// Objects are a convenient way to use singletons in Scala,
// here we use it as a simple factory for our `MutableAccount`
object MutableBank {

  def createAccount = new MutableAccount(50)

}

val acc1 = MutableBank.createAccount
val acc2 = MutableBank.createAccount

// Case classes are a different type of classes in Scala,
// they are immutable by default and come with other features and niceties
case class Account(id: Int, balance: Double = 0.0) {

  // We return a new account instead of keeping internal state
  def deposit(money: Double): Account = {
    Account(id, this.balance + money)
  }

  def withdraw(money: Double): Account = {
    if (this.balance >= money) {
      // We can also use the `copy` method to create new instances based
      // on an existing instance, with named parameters we can specify
      // which properties change and the rest remain the same
      this.copy(balance = this.balance - money)
    } else {
      this
    }
  }

}

// Another use case for factories, this time we added an `id` field
// in our `Account` class and inside our object we manage its creation
object Bank {

  private val random = scala.util.Random

  def createAccount = Account(random.nextInt, 50)

}

val acc = Bank.createAccount

acc.balance

val accDeposit = acc.deposit(100)

val accWithdrawal = accDeposit.withdraw(100)

accWithdrawal.balance

// We can think of functional programming as a flow of data that is
// modified at different steps of the pipeline
Bank.createAccount.deposit(100).withdraw(100).balance