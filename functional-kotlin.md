theme: Plain Jane
slidenumbers: true
autoscale: true

## Funktional __Kotlin__

---

![50%](https://logos-download.com/wp-content/uploads/2016/10/Kotlin_logo-700x700.png)
![125%](https://avatars2.githubusercontent.com/u/29458023?v=4&s=200)

## Agenda

- Functional Programming
- Arrow
- Types
- Effects

---

# Functional Programming

- Immutability
- Function (pure) & Higher Order Function
- Referential Transparency
- Abstractions
- ...

---

# Definition of pure function

- It returns the same result if given the same arguments (it is also referred as `deterministic`)
- It does not cause any observable side effects

^
It returns the same result if given the same arguments
no mutation or side effects
-> easier to test

---

# Arrow

![75%](https://avatars2.githubusercontent.com/u/29458023?v=4&s=200)

- Library for __Typed__ Functional Programming in __Kotlin__
- created by __47 Degrees__
- Inspired by __Cats__ and __ScalaZ__ from Scala ecosystem
- lots of modules
- `v0.8.2` (31/12/18)

---

Arrow aims to provide a _lingua franca_ of __interfaces__ and __abstractions__ across Kotlin libraries. For this, it includes the most popular __data types__, __type classes__ and abstractions such as `Option`, `Try`, `Either`, `IO`, `Functor`, `Applicative`, `Monad` to empower users to write pure FP apps and libraries built atop __higher order abstractions__.
> -- Arrow

^ Typeclasses are interfaces that define a set of extension functions associated to one type. You may see them referred as “extension interfaces”.

^ The other purpose of these interfaces, like with any other unit of abstraction, is to have a single shared definition of a common API and behavior shared across many types in different libraries and codebases.

^ What differentiates FP from OOP is that these interfaces are meant to be implemented outside of their types, instead of by the types. Now, the association is done using generic parametrization rather than subclassing by implementing the interface. This has multiple benefits:
Typeclasses can be implemented for any class, even those not in the current project

^ You can treat typeclass implementations as stateless parameters because they’re just a collection of functions
You can make the extensions provided by a typeclass for the type they’re associated with by using functions like run and with.

---

# Data Types

[.code-highlight: 1]
[.code-highlight: 3]
[.code-highlight: 5]
[.code-highlight: 7]

```
- Option

- Either

- Try

- Validated
```

^ A datatype is a an abstraction that encapsulates one reusable coding pattern. These solutions have a canonical implementation that is generalised for all possible uses.

^ Arrow models the absence of values through the Option datatype similar to how Scala, Haskell and other FP languages handle optional values.


---

# Effects

```
- IO
```

^ IO is the most common data type used to represent side-effects in functional languages. This means IO is the data type of choice when interacting with the external environment: databases, network, operative systems, files…

^ IO is used to represent operations that can be executed lazily and are capable of failing, generally with exceptions. This means that code wrapped inside IO will not throw exceptions until it is run, and those exceptions can be captured inside IO for the user to check.

^ The first challenge for someone new to effects withIOis evaluating its result. Given that IO is used to wrap operations with the environment, the return value after completion is commonly used in another part of the program. Coming from an OOP background the simplest way to use the return value of IO is to consider it as an asynchronous operation you can register a callback for.

^ IO objects can be constructed passing them functions that will not be immediately called. This is called lazy evaluation. Running in this context means evaluating the content of an IO object, and propagating its result in a synchronous, asynchronous, or deferred way.

^ Note that IO objects can be run multiple times, and depending on how they are constructed they will evaluate its content again every time they’re run.

^ The general good practice is to have a single unsafe run call per program, at the entry point. In backend applications or command line tools this can be at the main. For Android apps, specially those before Android 9.0, this could happen per Activity.

---

# What have we seen?

![right 200%](http://icons.iconarchive.com/icons/webalys/kameleon.pics/512/Checklist-icon.png)

- A little of FP

- 4 DataTypes (Option, Either, )

- 1 Effect: IO

---

# What Next?

![right 100%](https://avatars2.githubusercontent.com/u/29458023?v=4&s=200)

- Deep down into TypeClass (Applicative, Monad, Monoid, Comonad, etc.)

- Optics DSL

- Free (Product, Coproduct)

- Generics

---

# Take Away

![](https://www.mediaan.com/wp-content/uploads/2017/01/launch17.jpg)

- https://kotlinlang.org/

- https://arrow-kt.io

- https://gitter.im/arrow-kt/Lobby

- https://github.com/pgdejardin/functional-kotlin 
