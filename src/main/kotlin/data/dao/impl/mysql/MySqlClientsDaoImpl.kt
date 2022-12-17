package data.dao.impl.mysql

import data.dao.ClientsDao
import data.utils.DBConnector
import data.utils.DBService
import data.utils.mapping.toUser
import data.utils.tables.ClientsTable
import domain.entity.users.User
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MySqlClientsDaoImpl(
    private val dbConnector: DBConnector
) : ClientsDao {

    override suspend fun getById(clientId: Int): User? {

        var user: User? = null

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ClientsTable.GET_BY_ID)
            statement?.setInt(1, clientId)

            resultSet = statement?.executeQuery()

            if (resultSet?.next() == true) {
                user = resultSet.toUser()
            }

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeResultSet(resultSet)
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return user
    }

    override suspend fun getByTrainerId(trainerId: Int, offset: Int, rowCount: Int): List<User> {

        val result = mutableListOf<User>()

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ClientsTable.GET_BY_TRAINER_ID)

            statement?.apply {
                setInt(1, trainerId)
                setInt(2, offset)
                setInt(3, rowCount)
            }

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                result.add(resultSet.toUser())
            }

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeResultSet(resultSet)
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return result
    }

    override suspend fun getByLogin(login: String): User? {

        var user: User? = null

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ClientsTable.GET_BY_LOGIN)
            statement?.setString(1, login)

            resultSet = statement?.executeQuery()

            if (resultSet?.next() == true) {
                user = resultSet.toUser()
            }

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeResultSet(resultSet)
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return user
    }

    override suspend fun getByEmail(email: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun create(item: User): Boolean {

        if (item.password == null) {
            return false
        }

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ClientsTable.SAVE)

            statement?.apply {
                setString(1, item.name)
                setString(2, item.surname)
                setString(3, item.login)
                setString(4, item.password)
                setString(5, item.phoneNumber)
                setString(6, item.email)
            }

            return statement?.executeUpdate() == 1

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return false
    }

    override suspend fun update(item: User): Boolean {

        if (item.password == null) {
            return false
        }

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ClientsTable.UPDATE)

            statement?.apply {
                setString(1, item.name)
                setString(2, item.surname)
                setString(3, item.login)
                setString(4, item.password)
                setString(5, item.phoneNumber)
                setString(6, item.email)
                setInt(7, item.id)
            }

            return statement?.executeUpdate() == 1

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return false
    }

    override suspend fun delete(item: User): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ClientsTable.DELETE)
            statement?.setInt(1, item.id)

            return statement?.executeUpdate() == 1

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return false
    }
}