package data.utils.tables

import org.intellij.lang.annotations.Language

object CompletedSetsTable {

    @Language("sql")
    const val GET_BY_EXERCISE_ID = "select * from completed_sets where exercise_id = ?"

    @Language("sql")
    const val SAVE = "insert into completed_sets (set_number, repetitions, weight, c_time, notes, exercise_id) values (?, ?, ?, ?, ?, ?)"

    @Language("sql")
    const val UPDATE = "update completed_sets set set_number = ?, repetitions = ?, weight = ?, c_time = ?, notes = ? where id = ?"

    @Language("sql")
    const val DELETE = "delete from completed_sets where id = ?"
}