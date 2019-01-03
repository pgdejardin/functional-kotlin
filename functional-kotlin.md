theme: Plain Jane
slidenumbers: true
autoscale: true

## Functional __Kotlin__

---

![50%](https://logos-download.com/wp-content/uploads/2016/10/Kotlin_logo-700x700.png)
![125%](https://avatars2.githubusercontent.com/u/29458023?v=4&s=200)

## Agenda

- FP?
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
- Inspired by __Cats__ and __ScalaZ__ from Scala ecosystem
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
