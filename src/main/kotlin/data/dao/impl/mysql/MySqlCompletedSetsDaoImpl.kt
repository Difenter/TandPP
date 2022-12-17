package data.dao.impl.mysql

import data.dao.CompletedSetsDao
import data.utils.DBConnector
import data.utils.DBService
import data.utils.mapping.toCompletedSet
import data.utils.mapping.toSql
import data.utils.tables.CompletedSetsTable
import domain.entity.workout.CompletedSet
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MySqlCompletedSetsDaoImpl(
    private val dbConnector: DBConnector
) : CompletedSetsDao {

    override suspend fun getByExerciseId(exerciseId: Long): List<CompletedSet> {

        val result = mutableListOf<CompletedSet>()

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(CompletedSetsTable.GET_BY_EXERCISE_ID)
            statement?.setLong(1, exerciseId)

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                result.add(resultSet.toCompletedSet())
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

    override suspend fun create(item: CompletedSet): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(CompletedSetsTable.SAVE)

            statement?.apply {
                setByte(1, item.setNumber)
                setShort(2, item.repetitions)
                setShort(3, item.weight ?: 0)
                setTime(4, item.time?.toSql())
                setString(5, item.notes)
                setLong(6, item.exerciseId)
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

    override suspend fun update(item: CompletedSet): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(CompletedSetsTable.UPDATE)

            statement?.apply {
                setByte(1, item.setNumber)
                setShort(2, item.repetitions)
                setShort(3, item.weight ?: 0)
                setTime(4, item.time?.toSql())
                setString(5, item.notes)
                setLong(6, item.id)
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

    override suspend fun delete(item: CompletedSet): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(CompletedSetsTable.DELETE)
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