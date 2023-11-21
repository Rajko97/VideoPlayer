package tv.brid.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YoutubeService {

    companion object {
        //const val API_KEY = "AIzaSyDmQpWniE4rbJmWZ7dWSRtAXN507zrisgw"
        const val API_KEY = "AIzaSyCJilVHBQCgD2f08kN8ogPy_4oN4bQoH-c"

        private const val BASE_URL = "https://www.googleapis.com/"

        fun build(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}