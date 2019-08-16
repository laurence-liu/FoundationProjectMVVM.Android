package liuuu.laurence.foundationprojectmvvmandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import liuuu.laurence.foundationprojectmvvmandroid.model.GitHubUser
import liuuu.laurence.foundationprojectmvvmandroid.network.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class GitHubFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return inflater.inflate(R.layout.fragment_github, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job = Job()

        val coroutineScope = CoroutineScope(Dispatchers.Main + job)

        coroutineScope.launch {
            val gitHubDeferred = GitHubApi.retrofitService.getGitHubUser(10)

            try {
                val gitHubResponse = gitHubDeferred.await()
                Log.i("OK Google", gitHubResponse[0].avatarUrl)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

//        GitHubApi.retrofitService.getGitHubUser(10).enqueue(object: Callback<List<GitHubUser>> {
//            override fun onResponse(call: Call<List<GitHubUser>>, response: Response<List<GitHubUser>>) {
//                Log.i("OK Google", response.body()!![0].avatarUrl)
//            }
//
//            override fun onFailure(call: Call<List<GitHubUser>>, t: Throwable) {
//                Log.i("Google OK", t.toString())
//            }
//        })
    }
}