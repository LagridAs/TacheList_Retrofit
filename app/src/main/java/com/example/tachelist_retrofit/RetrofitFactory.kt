package com.example.tachelist_retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun makeRetrofitService(): TacheInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TacheInterface::class.java)
    }
}