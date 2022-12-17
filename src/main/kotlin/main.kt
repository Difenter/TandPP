import data.dao.impl.mysql.MySqlClientsDaoImpl
import data.dao.observable.DefaultObserver
import data.dao.observable.Observer
import data.factory.impl.*
import domain.entity.users.User

suspend fun main() {

    val clientsDao = MySqlClientsDaoFactory().create() as MySqlClientsDaoImpl

    val user = User(
        name = "name2",
        surname = "surname2",
        login = "login2",
        password = "12345678",
        phoneNumber = "0996661313",
        email = "email2"
    )

    clientsDao.run {

        addOnCreateListener(object : Observer<User> {
            override fun notifyOnEventHappened(item: User) {
                println("create $item")
            }
        })
        addOnUpdateListener(DefaultObserver())
        addOnDeleteListener {
            println("delete $it")
        }

        create(user)
        update(user.copy(name = "another name"))
        delete(user)
    }
}