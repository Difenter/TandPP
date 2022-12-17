import data.proxy.MySqlClientsProtectionProxy
import domain.entity.users.User
import domain.entity.users.UserRole

suspend fun main() {

    val clientsProxy = MySqlClientsProtectionProxy()

    val user = User(
        id = 1,
        name = "user name",
        surname = "user surname",
        login = "login",
        password = "12345678",
        phoneNumber = "0996661313",
        email = "email",
        userRole = UserRole.USER
    )

    val admin = User(
        id = 2,
        name = "admin name",
        surname = "admin surname",
        login = "login2",
        password = "12345678",
        phoneNumber = "0996661313",
        email = "email",
        userRole = UserRole.ADMIN
    )

    clientsProxy.run {
        create(user)
        create(admin)
        println(getById(1))
        println(getById(2))
        try {
            update(user.copy(name = "new user name"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        update(admin.copy(name = "new admin name"))
        println(getById(1))
        println(getById(2))
    }
}