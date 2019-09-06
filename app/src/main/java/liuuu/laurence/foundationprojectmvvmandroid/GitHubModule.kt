package liuuu.laurence.foundationprojectmvvmandroid

import liuuu.laurence.foundationprojectmvvmandroid.detail.DetailViewModel
import liuuu.laurence.foundationprojectmvvmandroid.github.GitHubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gitHubModule = module {
//    // single instance of HelloRepository
//    single<HelloRepository> { HelloRepositoryImpl() }
//
//    // Simple Presenter Factory
//    factory { MySimplePresenter(get()) }
//
//    // Simple Java Presenter
//    factory { MyJavaPresenter(get()) }
//
//    // scope for MyScopeActivity
//    scope(named<MyScopeActivity>()) {
//        // scoped MyScopePresenter instance
//        scoped { MyScopePresenter(get()) }
//    }

    viewModel {
        GitHubViewModel()
    }

    viewModel { (login : String) ->
        DetailViewModel(login)
    }
}