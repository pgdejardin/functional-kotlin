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
  try {
    val categories = getCategories()
    // ...
  } catch (e: NoConnectionException) {
    println(e.message)
  } catch (e: AuthorizationException) {
    println(e.message)
  }

  val something = Try { getCategories() }
    .toEither()
    .mapLeft {
      DomainError.NoConnectionError("Failed to fetch categories", it)
    }

  val list = when (something) {
    is Either.Left -> {
      println(something.a.message)  // Envoie des logs, traitement de l'erreur pour un client, etc.
      emptyList()
    }
    is Either.Right -> something.b
  }

  // Autre façon de faire
  val list2: List<String> = something.fold({ println(it.message); emptyList() }, { it })

  println(list)
  println(list2)
}


