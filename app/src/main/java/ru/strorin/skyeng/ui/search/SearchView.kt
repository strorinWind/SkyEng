package ru.strorin.skyeng.ui.search

import ru.strorin.skyeng.data.Translation

interface SearchView {

    fun setNetworkError()

    fun setTranslationsList(list: List<Translation>)
}