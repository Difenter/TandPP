package data.factory.impl

import data.dao.RequestsDao
import data.dao.impl.mysql.MySqlRequestsDaoImpl
import data.factory.DaoFactory
import data.utils.MySqlDBConnector2

class MySqlRequestsDaoFactory : DaoFactory<RequestsDao> {
    override fun create(): RequestsDao = MySqlRequestsDaoImpl(MySqlDBConnector2)
}