package domain.memento

sealed class Action<T>(open val state: T) {

    data class Create<T>(override val state: T) : Action<T>(state)

    data class Update<T>(override val state: T) : Action<T>(state)

    data class Delete<T>(override val state: T) : Action<T>(state)
}
