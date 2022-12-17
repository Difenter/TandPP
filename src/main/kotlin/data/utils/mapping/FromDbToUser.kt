package data.utils.mapping

import domain.entity.users.User
import java.sql.ResultSet

fun ResultSet.toUser() = User(
        id = this.getInt(1),
        name = this.getString(2),
        surname = this.getString(3),
        login = this.getString(4),
        password = this.getString(5),
        phoneNumber = this.getString(6),
        email = this.getString(7)
)