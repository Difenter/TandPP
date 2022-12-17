package data.utils.mapping

import domain.entity.workout.Time

fun java.sql.Time.fromSql(): Time {

    val timeString = this.toString()
    val minutes = timeString.split(":")[1].toByte()
    val seconds = timeString.split(":")[2].toByte()

    return Time(minutes, seconds)
}

fun Time.toSql() =
        java.sql.Time.valueOf("00:${this.minutes}:${this.seconds}")
