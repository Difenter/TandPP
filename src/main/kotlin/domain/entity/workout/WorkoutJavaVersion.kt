package domain.entity.workout

import java.util.*

/*
Java-style Builder pattern
 */

class WorkoutJavaVersion private constructor(builder: Builder) {

    val id: Long
    val type: String
    val date: Date
    val clientId: Int
    var exercises: List<Exercise>

    init {
        id = builder.id
        type = builder.type
        date = builder.date
        clientId = builder.clientId
        exercises = builder.exercises
    }

    override fun toString(): String {
        return "WorkoutJavaVersion(id=$id, type='$type', date=$date, clientId=$clientId, exercises=\n${exercises.map { "$it\n" }})"
    }

    class Builder(
        val type: String,
        val date: Date,
        val clientId: Int
    ) {

        var id = 0L
            private set

        private val _exercises = mutableListOf<Exercise>()
        val exercises: List<Exercise> = _exercises

        fun setId(id: Long): Builder {
            this.id = id
            return this
        }

        fun addExercise(exercise: Exercise): Builder {
            _exercises.add(exercise)
            return this
        }

        fun build() = WorkoutJavaVersion(this)
    }
}