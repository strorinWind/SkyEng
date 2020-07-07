package ru.strorin.skyeng.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {

    @GET("api/public/v1/words/search")
    fun wordSearch(@Query("search") search: String): Single<Response<List<WordTranslationDao>>>

}