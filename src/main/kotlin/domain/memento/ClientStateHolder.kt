package domain.memento

import data.dao.ClientsDao
import domain.entity.users.User

// Caretaker class
class ClientStateHolder(private val dao: ClientsDao) {

    private val actions = mutableListOf<Action<User>>()

    fun create(user: User) {
        if (actions.none { it is Action.Create } || actions.isEmpty()) {
            actions.add(Action.Create(user))
        } else {
            throw IllegalStateException("You need to create object before performing actions on it")
        }
    }

    fun update(user: User) {
        if (actions.lastOrNull() !is Action.Delete) {
            actions.add(Action.Update(user))
        } else {
            throw IllegalStateException("You can not perform any action on object after it has been deleted")
        }
    }

    fun delete(user: User) {
        actions.add(Action.Delete(user))
    }

    fun revert() = actions.removeLast().state

    fun getCurrentState() = actions.last().state

    suspend fun applyChanges() {
        for (action in actions) {

            when (action) {
                is Action.Delete -> {
                    dao.delete(action.state)
                    actions.clear()
                    break
                }
                is Action.Create -> dao.create(action.state)
                is Action.Update -> dao.update(action.state)
            }
        }
        actions.clear()
    }
}