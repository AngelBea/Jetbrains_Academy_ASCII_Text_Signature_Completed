package signature

import java.nio.file.Paths

/*
* Font Files
* */
val ROMAN_FILE_JETBRAINS = Paths.get("roman.txt").toAbsolutePath().toFile()
val MEDIUM_FILE_JETBRAINS = Paths.get("medium.txt").toAbsolutePath().toFile()
val REGEX_START_LETTER = "\\w\\s\\d{1,2}".toRegex()
val SPECIAL_CHAR_SPACE = ' '
val BORDER_LINE = "88  "