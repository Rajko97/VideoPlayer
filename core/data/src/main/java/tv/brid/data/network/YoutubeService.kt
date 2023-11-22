package tv.brid.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YoutubeService {

    companion object {
        const val API_KEY = "AIzaSyDmQpWniE4rbJmWZ7dWSRtAXN507zrisgw"

        private const val BASE_URL = "https://www.googleapis.com/"

        fun build(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}