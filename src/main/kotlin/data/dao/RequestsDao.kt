package data.dao

import domain.entity.users.RequestDB

interface RequestsDao : BaseDao<RequestDB> {

    suspend fun getByClientId(clientId: Int): List<RequestDB>

    suspend fun getByTrainerId(trainerId: Int): List<RequestDB>
}