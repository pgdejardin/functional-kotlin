package fr.xebia.xke.fpkt.types

import arrow.core.Either
import arrow.core.Try

open class GeneralException(msg: String) : Exception(msg)

class NoConnectionException(msg: String) : GeneralException(msg)

class AuthorizationException(msg: String) : GeneralException(msg)

fun checkPermissions() {
  throw AuthorizationException("No Authorization")
}

fun getCategoriesFromDistinctServer(): List<String> {
  throw NoConnectionException("Cannot Connect")
}

fun getCategories(): List<String> {
  checkPermissions()

  return getCategoriesFromDistinctServer()
}

sealed class DomainError(val message: String, val cause: Throwable) {
  class GeneralError(message: String, cause: Throwable) : DomainError(message, cause)
  class NoConnectionError(message: String, cause: Throwable) : DomainError(message, cause)
  class AuthorizationError(message: String, cause: Throwable) : DomainError(message, cause)
}

fun main() {
  val categoriesTry = Try { getCategories() }
    .toEither()
    .mapLeft {
      DomainError.NoConnectionError("Failed to fetch categories", it)
    }

  val categories = when (categoriesTry) {
    is Either.Left -> {
      println(categoriesTry.a.message)  // Envoie des logs, traitement de l'erreur pour un client, etc.
      emptyList()
    }
    is Either.Right -> categoriesTry.b
  }

  println(categories)

  // Autre façon de faire
  val categories2: List<String> =
    categoriesTry.fold(
      { println(it.message); emptyList() },
      { it }
    )

  println(categories2)
}
