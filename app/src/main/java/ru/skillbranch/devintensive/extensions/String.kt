package ru.skillbranch.devintensive.extensions

fun String.truncate( limit : Int = 16) : String =
    if(this.trim().length < limit) this.trim() else (this.trim().take(limit).trim() + "...")

fun String.stripHtml() : String {
   return "[\\s]{2,}".toRegex().replace(
       "<.*?>".toRegex().replace(this, "").filterNot { it in "&<>'\"" }, " ")
}