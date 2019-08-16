package liuuu.laurence.foundationprojectmvvmandroid.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(val login: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(login) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}