package ru.strorin.skyeng.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import ru.strorin.skyeng.data.Word
import ru.strorin.skyeng.network.ApiLoader
import ru.strorin.skyeng.network.SkyengApi
import ru.strorin.skyeng.network.WordTranslationDao
import java.lang.IllegalArgumentException

class SearchViewModel(
    private val api: SkyengApi
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var wordsList: List<WordTranslationDao> = emptyList()

    fun onSearchClicked(query: String, view: SearchView){
        wordsList = emptyList()
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

    fun onItemClicked(wordId: Int, view: SearchView) {
        wordsList
            .find { it.id == wordId }
            ?.let {
                view.openDetails(it)
            }
    }

    private fun transformToUiClassTranslation(view: SearchView, response: Response<List<WordTranslationDao>>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                wordsList = body

                val res = body
                    .map { dao ->
                        Word(
                            dao.id,
                            dao.text,
                            dao.meanings.joinToString(", ") { it.translation.text }
                        )
                    }
                view.setTranslationsList(res)
            } else {
                view.setNetworkError()
            }
        } else {
            view.setNetworkError()
        }
    }

    private fun handleError(e: Throwable, view: SearchView){
        e.printStackTrace()
        view.setNetworkError()
    }
}

class SearchViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(ApiLoader.skyengApi) as? T ?: throw IllegalArgumentException()
    }
}