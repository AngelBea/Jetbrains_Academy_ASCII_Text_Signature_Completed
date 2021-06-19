package signature

import java.io.File
import java.lang.IndexOutOfBoundsException

class Font(val file: File, vararg specialLetters : Letter, val letters: MutableList<Letter> = mutableListOf() ) {
    init {
        readFontFile()
        letters.addAll(specialLetters)
    }

    private fun readFontFile() {
        var letter: Letter = Letter()

        file.readLines().forEach { line ->
            if (REGEX_START_LETTER.matches(line)) {
                line.split(" ").let { letter = Letter(it.component1().first(), it.component2().toInt()) }
            } else {
                letter.letterLines.add(line)
            }

            if (letter.equivalent.isLetter()) letters.add(letter)
        }
    }

    fun findLetter(letter: Char): Letter {
        return letters.filter { it.equivalent.equals(letter) }.first()
    }

    fun constructPhrase(phrase: String) : Array<String> {
        val maximumSize = phrase.map { findLetter(it) }.filter { it.equivalent != SPECIAL_CHAR_SPACE }.maxOf { it.letterLines.size }.minus(1)
        val lettersInvolved = phrase.map { findLetter(it) }
        val phraseLines = Array(maximumSize.plus(1)) { "" }


        for (idx in maximumSize downTo 0) {
            phraseLines[idx] = lettersInvolved.joinToString(separator = ""){
                try {
                    if (it.letterLines.size < maximumSize.plus(1)) it.letterLines[idx - (maximumSize.plus(1) - it.letterLines.size)] else it.letterLines[idx]
                } catch (outOfBounds: IndexOutOfBoundsException) {
                    " ".repeat(it.length)
                }
            }
        }

        return phraseLines
    }
}

class Letter(val equivalent: Char = '!', var length: Int = 0, val letterLines: MutableList<String> = mutableListOf()) {
    fun printLetter() {
        letterLines.forEach(::println)
    }
}

class Letters {
    companion object {
        fun createSpecial(character: Char, verticalLength: Int, horizontalLength: Int): Letter {
            return with(Letter(character, horizontalLength)) {
                this.letterLines.addAll(Array(verticalLength) { character.toString().repeat(horizontalLength) })
                return@with this
            }
        }
    }
}

class Lines {
    companion object {
        fun createBorderLine(maxSize : Int, borderString : String) : String = borderString.repeat(maxSize)
    }
}

