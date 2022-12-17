package data.factory.impl

import data.dao.ClientsDao
import data.dao.impl.mysql.MySqlClientsDaoImpl
import data.factory.DaoFactory
import data.utils.MySqlDBConnector

class MySqlClientsDaoFactory : DaoFactory<ClientsDao> {
    override fun create(): ClientsDao = MySqlClientsDaoImpl(MySqlDBConnector.getInstance())
}