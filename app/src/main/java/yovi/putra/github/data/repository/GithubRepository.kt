package yovi.putra.github.data.repository

import io.reactivex.Observable
import yovi.putra.github.core.utils.thread.RxUtils
import yovi.putra.github.data.model.SearchResponse
import yovi.putra.github.data.remote.GithubApi

class GithubRepository(private val api: GithubApi) {

    fun searchUser(username: String?) : Observable<SearchResponse> =
        api.searchUsers(username).compose(RxUtils.applyObservableAsync())

}