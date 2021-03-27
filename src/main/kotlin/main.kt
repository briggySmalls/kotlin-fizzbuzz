/**
Helper function for checking if a value is divided exactly
 */
fun isDivisibleBy(value: Int, divisor: Int): Boolean {
    return value % divisor == 0
}

/**
 * Data class for holding a special word to print, if the test evaluates to true
 */
data class SpecialWord(val string: String, val test: (it: Int) -> Boolean)

/**
 * Parameterised constructor for creating fizzbuzz sequences
 */
fun fizzBuzzSequence(specials: List<SpecialWord>): Sequence<String> {
    var it = 1
    fun next(): String {
        // Run through testing all of our special words and accumulate a string
        var returnValue = specials.fold("") { acc, special -> if (special.test(it)) acc + special.string else acc }
        // If we've not had any matches at all, then return the default
        if (returnValue == "") {
            returnValue = "$it"
        }
        // Increment
        it++
        return returnValue
    }
    return generateSequence(::next)
}

fun main(args: Array<String>) {
    val params = listOf(
        SpecialWord("fizz") { isDivisibleBy(it, 3) },
        SpecialWord("buzz") { isDivisibleBy(it, 5) },
    )
    for (str in fizzBuzzSequence(params).take(100)) {
        println(str)
    }
}