package data.dao.observable

interface ObservableDao<T> {

    fun addOnCreateListener(listener: Observer<T>)

    fun addOnUpdateListener(listener: Observer<T>)

    fun addOnDeleteListener(listener: (T) -> Unit)
}

interface Observer<T> {
    fun notifyOnEventHappened(item: T)
}

class DefaultObserver<T> : Observer<T> {
    override fun notifyOnEventHappened(item: T) {
        println("Default observer: $item")
    }
}