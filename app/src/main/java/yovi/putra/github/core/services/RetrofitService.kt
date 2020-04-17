package yovi.putra.github.core.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yovi.putra.github.BuildConfig
import yovi.putra.github.core.utils.network.NoSSLSocketFactory
import java.util.concurrent.TimeUnit


object RetrofitService {
    inline fun <reified I> builder(api: String) : I {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val tlsSocket = NoSSLSocketFactory()
        val okHttp = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(tlsSocket, tlsSocket.trustManager)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(api)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(I::class.java)
    }

    inline fun <reified I> api() : I {
        return builder(BuildConfig.BASE_URL)
    }
}