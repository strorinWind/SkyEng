package ru.strorin.skyeng.ui.search

import ru.strorin.skyeng.data.Word
import ru.strorin.skyeng.network.WordTranslationDao

interface SearchView {

    fun setNetworkError()

    fun setTranslationsList(list: List<Word>)

    fun openDetails(word: WordTranslationDao)
}