package liuuu.laurence.foundationprojectmvvmandroid.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import liuuu.laurence.foundationprojectmvvmandroid.model.GitHubUserDetail
import liuuu.laurence.foundationprojectmvvmandroid.network.GitHubApi
import timber.log.Timber

class DetailViewModel(login: String) : ViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    private val _githubUserDetail = MutableLiveData<GitHubUserDetail>()
    val githubUserDetail: LiveData<GitHubUserDetail>
        get() = _githubUserDetail

    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _login.value = login
        getGitHubUserDetail()
    }

    private fun getGitHubUserDetail() {
        Timber.i("OKOKOKOKOKGOOGLE")
        Timber.i(login.value.toString())

        coroutineScope.launch {
            val githubUserDetailDeferred = GitHubApi.retrofitService.getGitHubUserDetail(login.value.toString())

            Timber.i(login.toString())

            try {
                val githubUserDetailResponse = githubUserDetailDeferred.await()
                _githubUserDetail.value = githubUserDetailResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}