package fr.xebia.xke.fpkt.types

import arrow.core.Either
import arrow.instances.either.monad.flatMap

fun parse(s: String): Either<NumberFormatException, Int> =
  if (s.matches(Regex("-?[0-9]+"))) Either.Right(s.toInt())
  else Either.Left(NumberFormatException("$s is not a valid integer."))

fun reciprocal(i: Int): Either<IllegalArgumentException, Double> =
  if (i == 0) Either.Left(IllegalArgumentException("Cannot take reciprocal of 0."))
  else Either.Right(1.0 / i)

fun stringify(d: Double): String = d.toString()

fun chain(s: String): Either<Exception, String> = parse(s).flatMap { reciprocal(it) }.map { stringify(it) }

// Either Style
fun main() {
  val x = chain("5")

  val value = when (x) {
    is Either.Left -> when (x.a) {
      is NumberFormatException -> "Not a number!"
      is IllegalArgumentException -> "Can't take reciprocal of 0!"
      else -> "Unknown error"
    }
    is Either.Right -> "Got reciprocal: ${x.b}"
  }
  print(value) // Got reciprocal: 0.2
}
