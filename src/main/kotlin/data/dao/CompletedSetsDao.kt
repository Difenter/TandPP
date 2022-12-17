package data.dao

import domain.entity.workout.CompletedSet

interface CompletedSetsDao : BaseDao<CompletedSet> {

    suspend fun getByExerciseId(exerciseId: Long): List<CompletedSet>
}