package data.utils.tables

import org.intellij.lang.annotations.Language

object ExerciseTable {

    @Language("sql")
    const val GET_BY_WORKOUT_ID = "select * from exercise where workout_id = ?"

    @Language("sql")
    const val SAVE = "insert into exercise (e_name, sets, repetitions_from, repetitions_to, weight_from, weight_to, e_time, notes, workout_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"

    @Language("sql")
    const val UPDATE = "update exercise set e_name = ?, sets = ?, repetitions_from = ?, repetitions_to = ?, weight_from = ?, weight_to = ?, e_time = ?, notes = ? where id = ?"

    @Language("sql")
    const val DELETE = "delete from exercise where id = ?"
}