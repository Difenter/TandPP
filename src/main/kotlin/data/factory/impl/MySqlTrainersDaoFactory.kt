package data.factory.impl

import data.dao.TrainersDao
import data.dao.impl.mysql.MySqlTrainersDaoImpl
import data.factory.DaoFactory
import data.utils.MySqlDBConnector

class MySqlTrainersDaoFactory : DaoFactory<TrainersDao> {
    override fun create(): TrainersDao = MySqlTrainersDaoImpl(MySqlDBConnector.getInstance())
}