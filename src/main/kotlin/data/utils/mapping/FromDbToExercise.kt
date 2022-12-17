package data.utils.mapping

import domain.entity.workout.Exercise
import java.sql.ResultSet

fun ResultSet.toExercise() = Exercise(
        id = this.getLong(1),
        name = this.getString(2),
        sets = this.getByte(3),
        repetitionsFrom = this.getShort(4),
        repetitionsTo = this.getShort(5),
        weightFrom = this.getShort(6),
        weightTo = this.getShort(7),
        time = this.getTime(8)?.fromSql(),
        notes = this.getString(9),
        workoutId = this.getLong(10)
)