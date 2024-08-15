package com.example.unittestingexample


import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {
    @GET("v2/rates/bitcoin")
    suspend fun getBitcoinRate(): RateResponse

    companion object {
        private const val BASE_URL = "https://api.coincap.io/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}

data class RateResponse(
    val data: RateData
)

data class RateData(
    val id: String,
    val symbol: String,
    val currencySymbol: String?,
    val rateUsd: String
)
