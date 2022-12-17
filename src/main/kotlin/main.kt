import data.dao.impl.mysql.MySqlClientsDaoImpl
import data.factory.impl.*
import domain.entity.users.User
import domain.memento.ClientStateHolder

suspend fun main() {

    val clientsDao = MySqlClientsDaoFactory().create() as MySqlClientsDaoImpl
    val clientStateHolder = ClientStateHolder(clientsDao)

    val user = User(
        id = 1,
        name = "name",
        surname = "surname",
        login = "login",
        password = "12345678",
        phoneNumber = "0996661313",
        email = "email"
    )

    clientStateHolder.run {
        create(user)
        val updatedUser = user.copy(name = "new name")
        update(updatedUser)
        println(clientStateHolder.getCurrentState())
        clientStateHolder.revert()
        println(clientStateHolder.getCurrentState())
        update(updatedUser)
        try {
            create(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        delete(updatedUser)
        try {
            update(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        revert()
        applyChanges()
        println(clientsDao.getById(updatedUser.id))
        delete(updatedUser)
        applyChanges()
        println(clientsDao.getById(updatedUser.id))
    }
}