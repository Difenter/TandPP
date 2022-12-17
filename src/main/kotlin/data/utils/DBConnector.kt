package data.utils

import java.sql.Connection

interface DBConnector {
    fun getConnection(): Connection
}