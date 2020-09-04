package ru.strorin.skyeng.network

import java.io.Serializable

data class WordTranslationDao(
    val id: Int,
    val text: String,
    val meanings: List<MeaningDao>
): Serializable

data class MeaningDao(
    val id: String,
    val translation: TranslationDao,
    val previewUrl: String,
    val imageUrl: String,
    val soundUrl: String
): Serializable

data class TranslationDao(
    val text: String
): Serializable