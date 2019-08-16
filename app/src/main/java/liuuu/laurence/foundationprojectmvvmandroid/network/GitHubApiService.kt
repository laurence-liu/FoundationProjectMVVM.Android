package liuuu.laurence.foundationprojectmvvmandroid.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import liuuu.laurence.foundationprojectmvvmandroid.model.GitHubUser
import liuuu.laurence.foundationprojectmvvmandroid.model.GitHubUserDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.github.com/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface GitHubApiService {
    @GET("users")
    fun getGitHubUser(@Query("since") since: Int): Deferred<List<GitHubUser>>

    @GET("users/{login}")
    fun getGitHubUserDetail(@Path("login") login: String): Deferred<GitHubUserDetail>
}

object GitHubApi {
    val retrofitService: GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java)
    }
}
