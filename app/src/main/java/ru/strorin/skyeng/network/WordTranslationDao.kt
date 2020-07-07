package ru.strorin.skyeng.network

data class WordTranslationDao(
    val id: Int,
    val text: String,
    val meanings: List<MeaningDao>
)

data class MeaningDao(
    val id: String,
    val translation: TranslationDao,
    val previewUrl: String,
    val imageUrl: String,
    val soundUrl: String
)

data class TranslationDao(
    val text: String
)