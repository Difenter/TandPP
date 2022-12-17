package data.utils

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

// Kotlin-style singleton

object MySqlDBConnector2 : DBConnector {

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
}