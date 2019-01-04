package fr.xebia.xke.fpkt.types

import arrow.core.*
import arrow.instances.option.monad.binding

fun step1() {
  val someString = Some("maybeAString")
  println(someString.t)

  val emptyValue = None
  println(emptyValue)
}

fun maybeIWillHaveAString(flag: Boolean) = if (flag) Some("I am a String") else None

val value1 = maybeIWillHaveAString(true)
val value2 = maybeIWillHaveAString(false)

fun step2() {
  println(value1.getOrElse { "" })
  println(value2.getOrElse { "" })
}

fun step3() {
  println(value1 is None)
  println(value2 is None)
}


fun step4() {
  val myString: String? = "Nullable string"
  val option = Option.fromNullable(myString)

  val myString2: String? = null
  val option2 = Option.fromNullable(myString2)

  println(option.getOrElse { "" })
  println(option2.getOrElse { "" })
}

fun step5() {
  val maybeAString = maybeIWillHaveAString(true)
  val aString = when (maybeAString) {
    is Some -> maybeAString.t
    is None -> ""
  }
  println(aString)
}

val number: Option<Int> = Some(3)
val noNumber: Option<Int> = None

fun step6() {
  val mappedResult1 = number.map { it * 1.5 }
  val mappedResult2 = noNumber.map { it * 1.5 }

  println(mappedResult1.getOrElse { 0 })
  println(mappedResult2.getOrElse { 0 })
}

fun step7() {
  val defaultNumber = { 1 }
  val operation = { x: Int -> x * 3 }

  val nine = number.fold(defaultNumber, operation)
  val defaultResult = noNumber.fold(defaultNumber, operation)

  println(nine)
  println(defaultResult)
}

fun step8() {
  1.some() // Some(1)
  1.toOption() // Some(1)
  none<Number>() // None

  val nullableValue: Number? = null
  nullableValue.toOption() // None

  val nullableValue2: Number? = 1
  nullableValue2.toOption() // Some(1)

  val listOfint = listOf(1, 2, 3)
  listOfint.firstOrNone() // Some(1)
  listOfint.firstOrNone { it == 2 } // Some(2)
  listOfint.firstOrNone { it == 4 } // None
}

fun step9() {
  val number = binding {
    val a = Some(1).bind()
    val b = Some(1 + a).bind()
    val c = Some(1 + b).bind()
    a + b + c
  }
  println(number) // Some(6)

  val noNumber = binding {
    val a = none<Int>().bind()
    val b = Some(1 + a).bind()
    val c = Some(1 + b).bind()
    a + b + c
  }
  println(noNumber) // None
}

fun main() {
  // Some & None
  step1()

  // Default value
  step2()

  // Check the option
  step3()

  // Create Option from nullable value
  step4()

  // Other way to get the value
  step5()

  // map
  step6()

  // fold
  step7()

  // extensions
  step8()

  // binding
  step9()
}
