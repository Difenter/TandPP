package data.utils.tables

import org.intellij.lang.annotations.Language

object WorkoutTable {

    @Language("sql")
    const val GET_BY_CLIENT_ID = "select * from workout where client_id = ? order by w_date desc limit ?, ?"

    @Language("sql")
    const val SAVE = "insert into workout (w_type, w_date, client_id) values (?, ?, ?)"

    @Language("sql")
    const val UPDATE = "update workout set w_type = ?, w_date = ? where id = ?"

    @Language("sql")
    const val DELETE = "delete from workout where id = ?"
}