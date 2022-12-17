package data.factory.impl

import data.dao.ExercisesDao
import data.dao.impl.mysql.MySqlExercisesDaoImpl
import data.factory.DaoFactory
import data.utils.MySqlDBConnector

class MySqlExercisesDaoFactory : DaoFactory<ExercisesDao> {
    override fun create(): ExercisesDao = MySqlExercisesDaoImpl(MySqlDBConnector.getInstance())
}