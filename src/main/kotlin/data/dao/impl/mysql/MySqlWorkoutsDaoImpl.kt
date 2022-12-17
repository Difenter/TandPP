package data.dao.impl.mysql

import data.dao.WorkoutsDao
import data.utils.DBConnector
import data.utils.DBService
import data.utils.mapping.toSql
import data.utils.mapping.toWorkout
import data.utils.tables.WorkoutTable
import domain.entity.workout.Workout
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MySqlWorkoutsDaoImpl(
    private val dbConnector: DBConnector
) : WorkoutsDao {

    override suspend fun getByClientId(clientId: Int, offset: Int, rowCount: Int): List<Workout> {

        val result = mutableListOf<Workout>()

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(WorkoutTable.GET_BY_CLIENT_ID)

            statement?.apply {
                setInt(1, clientId)
                setInt(2, offset)
                setInt(3, rowCount)
            }

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                result.add(resultSet.toWorkout())
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

    override suspend fun create(item: Workout): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(WorkoutTable.SAVE)

            statement?.apply {
                setString(1, item.type)
                setDate(2, item.date.toSql())
                setInt(3, item.clientId)
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

    override suspend fun update(item: Workout): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(WorkoutTable.UPDATE)

            statement?.apply {
                setString(1, item.type)
                setDate(2, item.date.toSql())
                setLong(3, item.id)
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

    override suspend fun delete(item: Workout): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(WorkoutTable.DELETE)
            statement?.setLong(1, item.id)

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