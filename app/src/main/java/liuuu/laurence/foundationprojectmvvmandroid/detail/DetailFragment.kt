package liuuu.laurence.foundationprojectmvvmandroid.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import liuuu.laurence.foundationprojectmvvmandroid.databinding.FragmentDetailBinding
import timber.log.Timber

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailBinding.inflate(inflater)

        val login = DetailFragmentArgs.fromBundle(arguments!!).login

        val viewModelFactory = DetailViewModelFactory(login)

        val detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.lifecycleOwner = this

        binding.detailViewModel = detailViewModel

        detailViewModel.githubUserDetail.observe(this, Observer {
            Timber.i(it.avatarUrl)
            Timber.i(it.blog)
            Timber.i(it.followersUrl)
        })

        return binding.root
    }
}