package fr.xebia.xke.fpkt.types

import arrow.core.Either

fun either(x: Any): Either<String, Any> {
  return when (x) {
    is String -> Either.left("I am a string")
    else -> Either.right(x::class)
  }
}
