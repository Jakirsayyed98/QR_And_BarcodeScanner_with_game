package com.jhcreativedeveloper.BarcodeScanner.Games.APIRequest

import com.jhcreativedeveloper.BarcodeScanner.BuildConfig
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.AllGames
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface myapi {
    //  @FormUrlEncoded
    @GET("games?id=4625")
    suspend fun getGameZopgames(
        //   @Path("id") id: String?,
    ): Response<AllGames>


    companion object {
        operator fun invoke(): myapi {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().apply {
                callTimeout(30, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG)
                    addInterceptor(logging)
            }.build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pub.gamezop.com/v3/")
                .client(client)
                .build()
                .create(myapi::class.java)
        }
    }
}