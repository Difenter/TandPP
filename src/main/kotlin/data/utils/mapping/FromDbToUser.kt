package data.utils.mapping

import domain.entity.users.User
import domain.entity.users.UserRole
import java.sql.ResultSet

fun ResultSet.toUser() = User(
        id = this.getInt(1),
        name = this.getString(2),
        surname = this.getString(3),
        login = this.getString(4),
        password = this.getString(5),
        phoneNumber = this.getString(6),
        email = this.getString(7),
        userRole = when (this.getInt(9)) {
            1 -> UserRole.ADMIN
            2 -> UserRole.USER
            else -> throw IllegalArgumentException()
        }
)