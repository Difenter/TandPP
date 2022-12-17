package data.dao.impl.mysql

import data.dao.RequestsDao
import data.utils.DBConnector
import data.utils.DBService
import data.utils.mapping.toRequest
import data.utils.tables.RequestTable
import domain.entity.users.RequestDB
import domain.entity.users.RequestStatus
import java.sql.*

class MySqlRequestsDaoImpl(
    private val dbConnector: DBConnector
) : RequestsDao {

    override suspend fun getByClientId(clientId: Int): List<RequestDB> {

        val result = mutableListOf<RequestDB>()

        var connection: Connection? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(RequestTable.GET_BY_CLIENT_ID)
            statement?.setInt(1, clientId)

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                result.add(resultSet.toRequest())
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

    override suspend fun getByTrainerId(trainerId: Int): List<RequestDB> {

        val result = mutableListOf<RequestDB>()

        var connection: Connection? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(RequestTable.GET_BY_TRAINER_ID)
            statement?.setInt(1, trainerId)

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                result.add(resultSet.toRequest())
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

    override suspend fun create(item: RequestDB): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(RequestTable.SAVE)

            statement?.apply {
                setInt(1, item.trainerId)
                setInt(2, item.clientId)
                setString(3, item.status.toString())
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

    override suspend fun update(item: RequestDB): Boolean {

        var connection: Connection? = null
        var updateStatement: PreparedStatement? = null
        var deleteStatement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            connection?.transactionIsolation = Connection.TRANSACTION_SERIALIZABLE
            connection?.autoCommit = false

            updateStatement = connection?.prepareStatement(RequestTable.UPDATE)
            deleteStatement = connection?.prepareStatement(RequestTable.DELETE_BY_CLIENT_ID)

            updateStatement?.apply {
                setString(1, item.status.toString())
                setInt(2, item.id)
            }

            deleteStatement?.setInt(1, item.clientId)

            val updateResult = updateStatement?.executeUpdate()

            if (item.status == RequestStatus.CONFIRMED) {

                val deleteResult = deleteStatement?.executeUpdate()

                return if (updateResult == 1 && deleteResult ?: 0 >= 1) {
                    connection?.commit()
                    true
                } else {
                    connection?.rollback()
                    false
                }
            }

            return if (updateResult == 1) {
                connection?.commit()
                true
            } else {
                connection?.rollback()
                false
            }

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeStatement(updateStatement)
            DBService.closeStatement(deleteStatement)
            DBService.closeConnection(connection)
        }

        return false
    }

    override suspend fun delete(item: RequestDB): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(RequestTable.DELETE)
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