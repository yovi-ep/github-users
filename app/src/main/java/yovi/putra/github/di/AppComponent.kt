package yovi.putra.github.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import yovi.putra.github.core.services.RetrofitService
import yovi.putra.github.data.remote.GithubApi
import yovi.putra.github.data.repository.GithubRepository
import yovi.putra.github.features.MainViewModel

val networkModule = module {
    single { RetrofitService.api<GithubApi>() }
}

val dataSourceModule = module {
    single { GithubRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)