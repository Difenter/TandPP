package data.factory

import data.dao.BaseDao

interface DaoFactory<T : BaseDao<*>> {
    fun create(): T
}