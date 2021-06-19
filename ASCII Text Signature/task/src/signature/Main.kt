package signature

fun main() {
    val romanFont = Font(ROMAN_FILE_JETBRAINS, Letters.createSpecial(SPECIAL_CHAR_SPACE, 20, 10))
    val mediumFont = Font(MEDIUM_FILE_JETBRAINS, Letters.createSpecial(SPECIAL_CHAR_SPACE, 20, 5))

    with(VisitorCard()){
        val nameAndStatus = askForNameAndStatus()
        insertPhrases(romanFont.constructPhrase(nameAndStatus[0]), mediumFont.constructPhrase(nameAndStatus[1]))
        print()
    }
}
