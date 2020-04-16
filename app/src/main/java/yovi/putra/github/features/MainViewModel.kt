package yovi.putra.github.features

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.github.core.base.BaseViewModel
import yovi.putra.github.core.utils.state.LoaderState
import yovi.putra.github.core.utils.state.ResultState
import yovi.putra.github.data.repository.GithubRepository

class MainViewModel(private val repo: GithubRepository) : BaseViewModel() {
    val searchResponse = MutableLiveData<ResultState>()

    fun searchUser(username: String?) {
        repo.searchUser(username)
            .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { searchResponse.postValue(ResultState.Success(it)) },
                { searchResponse.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }

}