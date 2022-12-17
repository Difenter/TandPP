package domain.entity.workout

import java.util.Date

/*
In Kotlin Builder pattern can be implemented
with default argument values and with named arguments
 */

data class Workout(
    val id: Long = 0,
    val type: String,
    val date: Date,
    val clientId: Int,
    var exercises: List<Exercise>? = null,
)