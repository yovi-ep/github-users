package yovi.putra.github.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import yovi.putra.github.data.model.SearchResponse

interface GithubApi {
    @GET("search/users")
    fun searchUsers(@Query("q") username: String?): Observable<SearchResponse>
}