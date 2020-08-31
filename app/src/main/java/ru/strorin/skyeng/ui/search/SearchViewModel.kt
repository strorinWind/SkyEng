package ru.strorin.skyeng.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import ru.strorin.skyeng.data.Translation
import ru.strorin.skyeng.network.ApiLoader
import ru.strorin.skyeng.network.SkyengApi
import ru.strorin.skyeng.network.WordTranslationDao
import java.lang.IllegalArgumentException

class SearchViewModel(
    private val api: SkyengApi
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()


    fun onSearchClicked(query: String, view: SearchView){
        val disposable = api
            .wordSearch(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { transformToUiClassTranslation(view, it) },
                { handleError(it, view) }
            )

        compositeDisposable.add(disposable)
    }

    private fun transformToUiClassTranslation(view: SearchView, response: Response<List<WordTranslationDao>>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val res = body
                    .map { it.meanings }
                    .flatten()
                    .map { Translation(it.translation.text) }
                Log.d("TESTTEST", res.toString())
                view.setTranslationsList(res)
            } else {
//                viewState.showErrorLoading()
            }
        } else {
//            viewState.showErrorLoading()
        }
    }

    private fun handleError(e: Throwable, view: SearchView){
        e.printStackTrace()
    }
}

class SearchViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(ApiLoader.skyengApi) as? T ?: throw IllegalArgumentException()
    }
}