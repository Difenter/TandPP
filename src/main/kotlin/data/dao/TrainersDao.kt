package data.dao

import domain.entity.users.User

interface TrainersDao : BaseDao<User> {

    suspend fun getNumberOfClients(trainerId: Int): Int?

    suspend fun getAll(offset: Int, rowCount: Int): List<User>

    suspend fun getById(id: Int): User?

    suspend fun getByNameAndSurnameOrLogin(
        name: String,
        surname: String,
        login: String,
        offset: Int,
        rowCount: Int
    ): List<User>

    suspend fun getTrainerByClientId(id: Int): User?

    suspend fun getByEmail(email: String): User?
}