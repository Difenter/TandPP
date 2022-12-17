package data.dao

import domain.entity.users.User

interface ClientsDao : BaseDao<User> {

    suspend fun getById(clientId: Int): User?

    suspend fun getByTrainerId(trainerId: Int, offset: Int, rowCount: Int): List<User>

    suspend fun getByLogin(login: String): User?

    suspend fun getByEmail(email: String): User?
}