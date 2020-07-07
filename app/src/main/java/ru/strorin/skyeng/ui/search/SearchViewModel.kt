package ru.strorin.skyeng.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.strorin.skyeng.network.ApiLoader
import ru.strorin.skyeng.network.SkyengApi
import java.lang.IllegalArgumentException

class SearchViewModel(
    private val api: SkyengApi
): ViewModel() {

    fun onSearchClicked(query: String){

    }
}

class SearchViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(ApiLoader.skyengApi) as? T ?: throw IllegalArgumentException()
    }
}