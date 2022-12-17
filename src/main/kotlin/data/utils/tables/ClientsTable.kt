package data.utils.tables

import org.intellij.lang.annotations.Language

object ClientsTable {

    @Language("sql")
    const val GET_BY_ID = "select * from client where id = ?"

    @Language("sql")
    const val GET_BY_TRAINER_ID = "select * from client where trainer_id = ? order by id limit ?, ?"

    @Language("sql")
    const val GET_BY_LOGIN = "select * from client where login = ?"

    @Language("sql")
    const val SAVE = "insert into client (c_name, surname, login, c_password, phone_number, email) values (?, ?, ?, ?, ?, ?)"

    @Language("sql")
    const val UPDATE = "update client set c_name = ?, surname = ?, login = ?, c_password = ?, phone_number = ?, email = ? where id = ?"

    @Language("sql")
    const val DELETE = "delete from client where id = ?"
}