package fr.xebia.xke.fpkt.effects

import arrow.core.Either
import arrow.effects.IO
import arrow.effects.instances.io.monad.binding
import arrow.effects.liftIO
import kotlin.random.Random

/* IO is the most common data type used to represent side-effects in functional languages.
   This means IO is the data type of choice when interacting with the external environment:
   databases, network, operative systems, files…
 */

val ioMonadExample: IO<Unit> = IO { println("hello") }
val ioMonadErrorExample: IO<Int> = IO { throw Exception("This a big error") }

val getNumerator: IO<Int> = Random(1).nextInt(1, 99).liftIO() // IO.just(1)
val getDenominator: IO<Int> = 0.liftIO()

fun main() {
  // Monad IO
  ioMonadExample.attempt().unsafeRunSync()

  ioMonadErrorExample.runAsync {
    it.fold({ left -> IO { println(left.message) } }, { right -> IO { println(right.toString()) } })
  }.unsafeRunSync()

  val result = binding {
    val numerator = getNumerator.bind()
    val denominator = getDenominator.bind()

    numerator / denominator
  }
    .attempt()
    .unsafeRunSync()

  when (result) {
    is Either.Left -> println(result.a)
    is Either.Right -> println(result.b)
  }
}
