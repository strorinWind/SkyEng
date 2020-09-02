package ru.strorin.skyeng.data

data class Translation(
    val text: String
)

data class TranslationDetails(
    val text: String,
    val meaningsList: List<String>,
    val imageUrl: String = ""
)