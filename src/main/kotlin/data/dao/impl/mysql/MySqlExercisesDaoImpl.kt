package data.dao.impl.mysql

import data.dao.ExercisesDao
import data.utils.DBConnector
import data.utils.DBService
import data.utils.mapping.toExercise
import data.utils.mapping.toSql
import data.utils.tables.ExerciseTable
import domain.entity.workout.Exercise
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MySqlExercisesDaoImpl(
    private val dbConnector: DBConnector
) : ExercisesDao {

    override suspend fun getByWorkoutId(workoutId: Long): List<Exercise> {

        val result = mutableListOf<Exercise>()

        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ExerciseTable.GET_BY_WORKOUT_ID)
            statement?.setLong(1, workoutId)

            resultSet = statement?.executeQuery()

            while (resultSet?.next() == true) {
                result.add(resultSet.toExercise())
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

    override suspend fun create(item: Exercise): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ExerciseTable.SAVE)

            statement?.apply {
                setString(1, item.name)
                setByte(2, item.sets)
                setShort(3, item.repetitionsFrom)
                setShort(4, item.repetitionsTo)
                setShort(5, item.weightFrom ?: 0)
                setShort(6, item.weightTo ?: 0)
                setTime(7, item.time?.toSql())
                setString(8, item.notes)
                setLong(9, item.workoutId)
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

    override suspend fun update(item: Exercise): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ExerciseTable.UPDATE)

            statement?.apply {
                setString(1, item.name)
                setByte(2, item.sets)
                setShort(3, item.repetitionsFrom)
                setShort(4, item.repetitionsTo)
                setShort(5, item.weightFrom ?: 0)
                setShort(6, item.weightTo ?: 0)
                setTime(7, item.time?.toSql())
                setString(8, item.notes)
                setLong(9, item.id)
            }

            return statement?.executeUpdate() == 1

        } catch (e :SQLException) {
            e.printStackTrace()
        } finally {
            DBService.closeStatement(statement)
            DBService.closeConnection(connection)
        }

        return false
    }

    override suspend fun delete(item: Exercise): Boolean {

        var connection: Connection? = null
        var statement: PreparedStatement? = null

        try {

            connection = dbConnector.getConnection()
            statement = connection?.prepareStatement(ExerciseTable.DELETE)
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