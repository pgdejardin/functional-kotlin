package fr.xebia.xke.fpkt.types

import arrow.Kind
import arrow.core.Either
import arrow.core.EitherPartialOf
import arrow.data.*
import arrow.instances.either.applicativeError.applicativeError
import arrow.instances.nonemptylist.semigroup.semigroup
import arrow.instances.validated.applicativeError.applicativeError
import arrow.typeclasses.ApplicativeError

sealed class ValidationError(val msg: String) {
  data class DoesNotContain(val value: String) : ValidationError("Did not contain $value")
  data class MinLength(val value: Int) : ValidationError("Minimal length of $value")
  data class NotValidPassword(val reasons: Nel<ValidationError>) : ValidationError("Not a valid password")
}

data class FormField(val label: String, val value: String)
data class Password(val value: String)

sealed class Rules<F>(A: ApplicativeError<F, Nel<ValidationError>>) : ApplicativeError<F, Nel<ValidationError>> by A {

  private fun FormField.contains(needle: Regex): Kind<F, FormField> =
    if (value.contains(needle)) just(this)
    else raiseError(ValidationError.DoesNotContain(needle.pattern).nel())

  private fun FormField.minLength(minLength: Int): Kind<F, FormField> =
    if (value.length >= minLength) just(this)
    else raiseError(ValidationError.MinLength(minLength).nel())

  fun FormField.validateEmail(): Kind<F, Password> =
    map(contains("[@.!?\\-_#$%&*=]+".toRegex()), minLength(8)) {
      Password(value)
    }.handleErrorWith { raiseError(ValidationError.NotValidPassword(it).nel()) }

  object ErrorAccumulationStrategy :
    Rules<ValidatedPartialOf<Nel<ValidationError>>>(Validated.applicativeError(NonEmptyList.semigroup()))

  object FailFastStrategy :
    Rules<EitherPartialOf<Nel<ValidationError>>>(Either.applicativeError())

  companion object {
    infix fun <A> failFast(f: FailFastStrategy.() -> A): A = f(FailFastStrategy)
    infix fun <A> accumulateErrors(f: ErrorAccumulationStrategy.() -> A): A = f(ErrorAccumulationStrategy)
  }

}

fun main() {
  val rules = Rules accumulateErrors {
    listOf(
      FormField("Invalid Password", "abcdefgijk"),
      FormField("Too Short Password", "@bcd"),
      FormField("Too Short And Not Strong Password", "abcd"),
      FormField("Valid Password", "1234abcd@!")
    ).map { it.validateEmail() }
  }

  rules.forEach { println(it) }
}
