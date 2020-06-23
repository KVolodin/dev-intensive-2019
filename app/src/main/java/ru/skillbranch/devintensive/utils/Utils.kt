package ru.skillbranch.devintensive.utils

import java.util.*

val transliterationMap = mapOf(
    'а' to "a", 'б' to "b", 'в' to "v",  'г' to "g",
    'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i",
    'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o",
    'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f",
    'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh",
    'щ' to "sh'", 'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya")

object Utils {

    fun parseFullName(fullName:String?) :Pair<String?,String?> {

        val isEmpty = fullName?.trim()?.isEmpty()
        if(isEmpty == null || isEmpty)
            return null to null
        val parts = fullName?.split(" ").toList()
        return  parts?.getOrNull(0) to parts.getOrNull(1)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var ret = ""
        for (ch in payload.trim()) {
            ret += if (ch != ' ') transliterationMap[ch.toLowerCase()].let {
                if(it == null)
                    return@let ch
                if(ch.isUpperCase()) it.toUpperCase(Locale.ROOT) else it
            } else divider
        }
        return ret
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first = firstName?.take(1)?.toUpperCase(Locale.ROOT)?.trim() ?: ""
        val second = lastName?.take(1)?.toUpperCase(Locale.ROOT)?.trim() ?: ""
        return if( (first + second).isNotEmpty() ) (first + second) else null
    }
}