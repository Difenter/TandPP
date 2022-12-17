package data.utils.mapping

import domain.entity.workout.CompletedSet
import java.sql.ResultSet

fun ResultSet.toCompletedSet() = CompletedSet(
        id = this.getLong(1),
        setNumber = this.getByte(2),
        repetitions = this.getShort(3),
        weight = this.getShort(4),
        time = this.getTime(5)?.fromSql(),
        notes = this.getString(6),
        exerciseId = this.getLong(7)
)