// Anonymous Function or Lambda
() => {}

// Named Function
val sum = (x: Int, y: Int) => x + y
sum(2, 3)

// No return type, it is inferred
// def multiply(x: Int, y: Int) = x * y
def multiply(x: Int, y: Int): Int = x + y

def fizzbuzz(n: Int): String = {
  val dividedBy3 = n % 3 == 0
  val dividedBy5 = n % 5 == 0

  if (dividedBy3 && dividedBy5) "fizzbuzz"
  else if (dividedBy3 && !dividedBy5) "fizz"
  else if (dividedBy5 && !dividedBy3) "buzz"
  else n.toString
}

/*

// Function body in a single expression

def fizzbuzz(n: Int): String =
  if (n % 3 == 0 && n % 5 == 0) {
    println("something")
    "fizzbuzz"
  }
  else if (n % 3 == 0 && n % 5 != 0) "fizz"
  else if (n % 5 == 0 && n % 3 != 0) "buzz"
  else n.toString
 */

fizzbuzz(1)
fizzbuzz(2)
fizzbuzz(3)
fizzbuzz(4)
fizzbuzz(5)
fizzbuzz(15)

// Nested Function

def factorial(n: Int): Int = {
  def helper(current: Int, acc: Int = 1): Int = {
    println(s"Current: $current - We have: $acc - We will have ${acc * current}")
    if (current == 1) acc
    else helper(current - 1, acc * current)
  }

  helper(n)
}

factorial(5)