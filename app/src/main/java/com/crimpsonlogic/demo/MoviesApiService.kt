package com.example.krishna.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiService {
    private val BASE_URL="http://www.omdbapi.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoiesAPIInterface::class.java)
    fun movieList(pageNum:Int,filter:String?):Call<Response>{
        return api.getMoviesListbasedOntheSearch(pageNum,filter)
    }
    fun movieDeatils(pageNum:Int,filter:String?):Call<Response>{
        return api.getMoviesListbasedOntheSearch(pageNum,filter)
    }
}