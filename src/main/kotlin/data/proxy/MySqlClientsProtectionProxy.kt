package data.proxy

import data.dao.ClientsDao
import data.factory.impl.MySqlClientsDaoFactory
import domain.entity.users.User
import domain.entity.users.UserRole

class MySqlClientsProtectionProxy : ClientsDao {

    private val clientsDao: ClientsDao = MySqlClientsDaoFactory().create()

    override suspend fun getById(clientId: Int) = clientsDao.getById(clientId)

    override suspend fun getByTrainerId(trainerId: Int, offset: Int, rowCount: Int) =
        clientsDao.getByTrainerId(trainerId, offset, rowCount)

    override suspend fun getByLogin(login: String) = clientsDao.getByLogin(login)

    override suspend fun getByEmail(email: String) = clientsDao.getByEmail(email)

    override suspend fun create(item: User) = clientsDao.create(item)

    override suspend fun update(item: User) = when (item.userRole) {
        UserRole.ADMIN -> clientsDao.update(item)
        UserRole.USER -> throw IllegalAccessException()
    }

    override suspend fun delete(item: User) = clientsDao.delete(item)
}