package data.utils

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

// Java-style singleton

class MySqlDBConnector private constructor(): DBConnector {

    private val properties =  Properties()

    private var user: String
    private var password: String
    private var url: String

    init {
        properties.load(javaClass.classLoader.getResourceAsStream("application.properties"))
        properties.run {
            user = getProperty("db.mysql.username")
            password = getProperty("db.mysql.password")
            url = getProperty("db.mysql.url")
        }
    }

    override fun getConnection(): Connection = DriverManager.getConnection(url, user, password)

    companion object {

        @Volatile
        private var instance: MySqlDBConnector? = null

        fun getInstance() = synchronized(this) {
            if (instance == null) {
                instance = MySqlDBConnector()
            }
            instance!!
        }
    }
}