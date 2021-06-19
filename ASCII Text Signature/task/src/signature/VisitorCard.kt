package signature

import java.util.stream.Stream

class VisitorCard(val phrases: MutableList<String> = mutableListOf()) {
    fun insertPhrases(vararg phraseArray: Array<String>) = phraseArray.forEach { phrases.addAll(it) }
    fun askForNameAndStatus() : MutableList<String> {
        val nameSurnameAndStatus = mutableListOf<String>()
        print("Enter name and surname: ")
        nameSurnameAndStatus.add(readLine()!!)
        print("Enter status: ")
        nameSurnameAndStatus.add(readLine()!!)

        return nameSurnameAndStatus
    }

    fun print() {
        val maxLength = phrases.maxByOrNull { it.length }!!.length
        val maxLengthCard = maxLength+BORDER_LINE.length*2
        Lines.createBorderLine(maxLengthCard, "8").let(::println)

        phrases.forEach { phrase ->
            var constructedPhrase = ("$BORDER_LINE$phrase${BORDER_LINE.reversed()}".takeIf { auxPhrase -> auxPhrase.length == maxLengthCard }
            ?: "$BORDER_LINE${" ".repeat((maxLength-phrase.length)/2)}$phrase${" ".repeat(((maxLength-phrase.length)/2)) }${BORDER_LINE.reversed()}")

            if (constructedPhrase.length < maxLengthCard) {
                constructedPhrase = buildString() {
                    append(constructedPhrase)
                    insert(this.length - 3, " ")
                }
            }

            constructedPhrase.let(::println)
        }

        Lines.createBorderLine(maxLengthCard, "8").let(::println)
    }
}