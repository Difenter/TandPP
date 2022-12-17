package data.factory.impl

import data.dao.WorkoutsDao
import data.dao.impl.mysql.MySqlWorkoutsDaoImpl
import data.factory.DaoFactory
import data.utils.MySqlDBConnector2

class MySqlWorkoutsDaoFactory : DaoFactory<WorkoutsDao> {
    override fun create(): WorkoutsDao = MySqlWorkoutsDaoImpl(MySqlDBConnector2)
}