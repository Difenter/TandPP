package data.utils.mapping

import domain.entity.workout.Workout
import java.sql.ResultSet
import java.util.*

fun ResultSet.toWorkout() = Workout(
        id = this.getLong(1),
        type = this.getString(2),
        date = this.getDate(3),
        clientId = this.getInt(4)
)

fun Date.toSql() = java.sql.Date(this.time)