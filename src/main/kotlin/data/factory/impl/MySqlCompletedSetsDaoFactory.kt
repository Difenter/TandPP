package data.factory.impl

import data.dao.CompletedSetsDao
import data.dao.impl.mysql.MySqlCompletedSetsDaoImpl
import data.factory.DaoFactory
import data.utils.MySqlDBConnector2

class MySqlCompletedSetsDaoFactory : DaoFactory<CompletedSetsDao> {
    override fun create(): CompletedSetsDao = MySqlCompletedSetsDaoImpl(MySqlDBConnector2)
}