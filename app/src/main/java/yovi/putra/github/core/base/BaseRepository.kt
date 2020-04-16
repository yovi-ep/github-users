package yovi.putra.github.core.base

import io.reactivex.disposables.CompositeDisposable


interface IBaseRepository {
    fun onCleared()
}

open class BaseRepository : IBaseRepository {
    protected var subscriber = CompositeDisposable()

    override fun onCleared() {
        subscriber.dispose()
    }
}