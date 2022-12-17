package data.dao

import domain.entity.workout.Workout

interface WorkoutsDao : BaseDao<Workout> {

    suspend fun getByClientId(clientId: Int, offset: Int, rowCount: Int): List<Workout>
}