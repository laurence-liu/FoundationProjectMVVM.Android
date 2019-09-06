package liuuu.laurence.foundationprojectmvvmandroid.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import liuuu.laurence.foundationprojectmvvmandroid.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailBinding.inflate(inflater)

        val login = DetailFragmentArgs.fromBundle(arguments!!).login

        val detailViewModel: DetailViewModel by viewModel { parametersOf(login) }

//        val viewModelFactory = DetailViewModelFactory(login)
//
//        val detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.lifecycleOwner = this

        binding.detailViewModel = detailViewModel

        detailViewModel.githubUserDetail.observe(this, Observer {
            binding.gitHubUserDetail = it
        })

        return binding.root
    }
}