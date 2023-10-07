package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn(value: Any): String {
    return when(value) {
        "Hello" -> "world"
        "Howdy", "Bonjour" -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(n1: Int, n2: Int): Int {
    return n1 + n2
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values

fun sub(n1: Int, n2: Int): Int {
    return n1 - n2
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments

fun mathOp(n1: Int, n2: Int, mathFunc: (Int, Int) -> Int): Int {
    return mathFunc(n1, n2)
}

// write a class "Person" with first name, last name and age

class Person(val firstName: String, val lastName: String, var age: Int) {
    val debugString: String = "[Person firstName:" + firstName + " lastName:" + lastName + " age:" + age + "]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator

class Money(val amount: Int, val currency: String) {
    private val allowedCurrencyArr = arrayOf("USD", "EUR", "CAN", "GBP")

    init {
        if (amount < 0 || !allowedCurrencyArr.contains(currency)) {
            throw IllegalArgumentException("Incorrect amount or currency type passed in.")
        }
    }

    fun convert(convertToCurrency: String): Money {
        if (!allowedCurrencyArr.contains(convertToCurrency)) {
            throw IllegalArgumentException("Incorrect currency type to convert to.")
        }

        var conversionRate: Double = 1.0
        if (this.currency != convertToCurrency) {
            if (this.currency == "USD") {
                conversionRate = when (convertToCurrency) {
                    "GBP" -> 1.0/2.0
                    "EUR" -> 3.0/2.0
                    else -> 5.0/4.0 // "CAN"
                }
            } else if (this.currency == "GBP") {
                conversionRate = when (convertToCurrency) {
                    "USD" -> 2.0
                    "EUR" -> 3.0
                    else -> 5.0/2.0 // "CAN"
                }
            } else if (this.currency == "EUR") {
                conversionRate = when (convertToCurrency) {
                    "USD" -> 2.0/3.0
                    "GBP" -> 1.0/3.0
                    else -> 5.0/6.0 // "CAN"
                }
            } else if (this.currency == "CAN") {
                conversionRate = when (convertToCurrency) {
                    "USD" -> 4.0/5.0
                    "GBP" -> 2.0/5.0
                    else -> 6.0/5.0 // "EUR"
                }
            }
        }
        return Money((this.amount * conversionRate).toInt(), convertToCurrency)
    }

    operator fun plus(other: Money): Money {
        val convertedAmount = other.convert(this.currency).amount
        return Money (this.amount + convertedAmount, this.currency)
    }
}