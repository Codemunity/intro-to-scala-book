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