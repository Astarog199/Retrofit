package com.example.lesson14.ui.main

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


private const val BASE_URL = "https://api.thecatapi.com"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()                   //указываем базовый URL для API. Этот URL будет использоваться для всех запросов.
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())    //добавляем конвертер для работы с данными в формате JSON, используя библиотеку MoshiConverterFactory.
        .build()

    val searchImageApi: SearchImageApi = retrofit.create(SearchImageApi::class.java) //инициализируем объект, который поможет приложению отправлять запросы и получать ответы от API.
}


/**
 * определение интерфейса, который будет предоставлять методы для работы с API.
 *
 */
interface SearchImageApi {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )

    /**
     *  Определение метода getCatImage
     *  Который возвращает объект типа Call с параметризированным типом List<Cat>.
     *  Call<List<Cat>> — тип возвращаемого значения, где List<Cat> означает, что мы ожидаем получить список котиков от API
     */
    @GET("/v1/images/search") //аннотация Retrofit, которая указывает, что метод getCatImage выполняет HTTP GET-запрос по указанному пути.
    fun getCatImage(@Query("limit") limit: Int = 1): Call<List<Cat>>
}