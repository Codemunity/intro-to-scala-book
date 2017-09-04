/*
  LIST CREATION

  This is how you normally create lists in Scala.
  It's also normal to specify the type as a parameter, like `List[String]`.

 */
val list: List[String] = List("hello", "", "world", "")

/*
  COMMON LIST OPERATIONS

  The following operations can be used to traverse the list manually
  as we'll see in the examples below.
 */

// To retrieve the list's first element, you can use the `head` method
list.head

// To retrieve all the elements but the first one
list.tail

// To append an element to the end of the list you cn use the `:+` method.
list :+ "!"
// Remember, the operation above does not mutate the `list` value,
// it creates a new one
list

/*
  HIGHER-ORDER FUNCTIONS
 */

def nonEmptyStrings(list: List[String]): List[String] = {
  def helper(currentList: List[String], acc: List[String] = List()): List[String] = {
    if (currentList.isEmpty) acc
    else if (currentList.head.nonEmpty) helper(currentList.tail, acc :+ currentList.head)
    else helper(currentList.tail, acc)
  }
  helper(list)
}

nonEmptyStrings(list)

def filterOutSpecificStrings(list: List[String]): List[String] = {
  def helper(currentList: List[String], acc: List[String] = List()): List[String] = {
    if (currentList.isEmpty) acc
    else if (currentList.head != "world" && currentList.head.nonEmpty)
      helper(currentList.tail, acc :+ currentList.head)
    else helper(currentList.tail, acc)
  }
  helper(list)
}

filterOutSpecificStrings(list)


def filter(list: List[String], predicate: (String) => Boolean): List[String] = {
  def helper(currentList: List[String], acc: List[String] = List()): List[String] = {
    if (currentList.isEmpty) acc
    else if (predicate(currentList.head)) helper(currentList.tail, acc :+ currentList.head)
    else helper(currentList.tail, acc)
  }
  helper(list)
}

filter(list, (string) => string.nonEmpty)
filter(list, (string) => string != "world" && string.nonEmpty)


/*

  BUILT-IN HIGHER-ORDER FUNCTIONS

 */

// FILTER

list.filter((string) => string.nonEmpty)
list.filter((string) => string != "world" && string.nonEmpty)

// MAP

// We don't have wrap the `string` parameter in parenthesis,
// because the anonymous function only has one parameter.
val stringLengths = list.map(string => string.length)

val defaultStrings = list.map(string => if (string.isEmpty) "default" else string)

// FLATMAP

val listOfLists: List[List[Int]] = List(List(1,2,3), List(4,5,6), List(7,8))

// Approach with the nested `map`s and `flatten` function
listOfLists.map(nestedList => nestedList.map(int => int * 2)).flatten

// Using the `flatMap` function.
listOfLists.flatMap(ints => ints.map(int => int * 2))

// ZIP

val names = List("Bob", "Fred", "Ted", "John")
val ages = List(34, 47, 24, 63)

// How to create a tuple
val bob: (String, Int) = ("Bob", 34)
// How to access Bob's name
bob._1
// How to access Bob's age
bob._2

// Zip the names with the ages
val people = names.zip(ages)

// People over 40 years old
people.filter(person => person._2 >= 40)