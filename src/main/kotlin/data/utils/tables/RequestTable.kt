package data.utils.tables

import org.intellij.lang.annotations.Language

object RequestTable {

    @Language("sql")
    const val GET_BY_CLIENT_ID = "select * from request where client_id = ?"

    @Language("sql")
    const val GET_BY_TRAINER_ID = "select * from request where trainer_id = ?"

    @Language("sql")
    const val SAVE = "insert into request (trainer_id, client_id, r_status) values (?, ?, ?)"

    @Language("sql")
    const val UPDATE = "update request set r_status = ? where id = ?"

    @Language("sql")
    const val DELETE = "delete from request where id = ?"

    @Language("sql")
    const val DELETE_BY_CLIENT_ID = "delete from request where client_id = ?"
}