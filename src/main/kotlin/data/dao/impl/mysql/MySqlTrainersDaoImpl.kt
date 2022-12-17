package data.dao.impl.mysql

import data.dao.TrainersDao
import data.utils.DBConnector
import data.utils.DBService
import data.utils.mapping.toUser
import data.utils.tables.TrainersTable
import domain.entity.users.User
import java.sql.*

class MySqlTrainersDaoImpl(
    private val dbConnector: DBConnector
) : TrainersDao {


    override suspend fun getNumberOfClients(trainerId: Int): Int? {

        var connection: Connection? = null
        var statement: CallableStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareCall(TrainersTable.GET_NUMBER_OF_CLIENTS)

            statement?.setInt(2, trainerId)
            resultSet = statement?.executeQuery()

            var result: Int? = null

            if (resultSet?.next() == true) {
                result = resultSet.getInt(1)
            }

            return result

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeResultSet(resultSet)
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return null
    }

    override suspend fun getAll(offset: Int, rowCount: Int): List<User> {

        val result = mutableListOf<User>()

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()

            statement = connection?.prepareStatement(TrainersTable.GET_ALL)

            statement?.apply {
                setInt(1, offset)
                setInt(2, rowCount)
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

    override suspend fun getById(id: Int): User? {

        var user: User? = null

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()

            statement = connection?.prepareStatement(TrainersTable.GET_BY_ID)
            statement?.setInt(1, id)

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

    override suspend fun getByNameAndSurnameOrLogin(
            name: String,
            surname: String,
            login: String,
            offset: Int,
            rowCount: Int
    ): List<User> {

        var users = mutableListOf<User>()

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()

            statement = connection?.prepareStatement(TrainersTable.GET_BY_NAME_AND_SURNAME_OR_LOGIN)

            statement?.apply {
                setString(1, name)
                setString(2, surname)
                setString(3, login)
                setInt(4, offset)
                setInt(5, rowCount)
            }

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                users.add(resultSet.toUser())
            }

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeResultSet(resultSet)
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return users
    }

    override suspend fun getTrainerByClientId(id: Int): User? {

        var trainer: User? = null

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(TrainersTable.GET_TRAINER_BY_CLIENT_ID)
            statement?.setInt(1, id)

            resultSet = statement?.executeQuery()

            if (resultSet?.next() == true) {
                trainer = resultSet.toUser()
            }

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeResultSet(resultSet)
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return trainer
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
            statement = connection?.prepareStatement(TrainersTable.SAVE)

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
            statement = connection?.prepareStatement(TrainersTable.UPDATE)

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
            statement = connection?.prepareStatement(TrainersTable.DELETE)
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