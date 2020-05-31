package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.round

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern,Locale("ru"))
    return dateFormat.format(this)
}

fun  Date.add(value:Int, units: TimeUnits = TimeUnits.MINUTE) : Date {
     this.time += when (units) {
        TimeUnits.SECOND    -> value * SECOND
        TimeUnits.MINUTE    -> value * MINUTE
        TimeUnits.HOUR      -> value * HOUR
        TimeUnits.DAY       -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(): String {
    fun getString(units : TimeUnits, time : Double) : String =
        if(time > 0)    "${units.plural(time.absoluteValue.toInt())} назад"
        else            "через ${units.plural(time.absoluteValue.toInt())}"

    val milliseconds = Date().time - time
    return when (milliseconds.absoluteValue){
        in 0..1* SECOND ->"только что"
        in 2* SECOND..45* SECOND ->if(milliseconds > 0) "несколько секунд назад" else "через несколько секунд"
        in 46* SECOND..(45 * MINUTE) ->getString(TimeUnits.MINUTE, round(milliseconds.toDouble()  / MINUTE.toDouble()) )
        in (46 * MINUTE)..(22 * HOUR) -> getString(TimeUnits.HOUR,round(milliseconds.toDouble() / HOUR.toDouble()) )
        in (22 * HOUR)..(360 * DAY) ->getString(TimeUnits.DAY,round(milliseconds.toDouble()  / DAY.toDouble()) )
        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;
    fun plural( i : Int ) : String {
        return when(this) {
            SECOND -> "$i ${getPluralSecondsOrSeconds(i,"секунд")}"
            MINUTE -> "$i ${getPluralSecondsOrSeconds(i,"минут")}"
            HOUR ->  "$i ${getPluralHour(i)}"
            DAY ->  "$i ${getPluralDay(i)}"
        }
    }
    private fun getPluralSecondsOrSeconds(i: Int,str:String): String {
        return str + when (i%20) {
            1-> "у"
            2,3,4-> "ы"
            else -> ""
        }
    }
    private fun getPluralHour( i : Int ) : String {
        return when (i%20) {
            1-> "час"
            2,3,4-> "часа"
            else -> "часов"
        }
    }
    private fun getPluralDay(i: Int): String {
        return when (i%20) {
            1-> "день"
            2,3,4-> "дня"
            else -> "дней"
        }
    }
}