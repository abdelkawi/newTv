package com.eurisko.alballam.tv.data

import com.eurisko.alballam.tv.data.model.AddToFavResponse
import com.eurisko.alballam.tv.data.model.ChangeLangResponse
import com.eurisko.alballam.tv.data.model.FavsResponse
import com.eurisko.alballam.tv.data.model.HomeDashboardBean
import com.eurisko.alballam.tv.data.model.ListingResponse
import com.eurisko.alballam.tv.data.model.LoginBean
import com.eurisko.alballam.tv.data.model.MovieAdBean
import com.eurisko.alballam.tv.data.model.MovieDetailBean
import com.eurisko.alballam.tv.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
  @POST("authentication/authenticate")
  @FormUrlEncoded
  suspend fun login(
    @Field("appUserEmail") email: String,
    @Field("appUserPasscode") password: String,
    @Field("loginType") loginType: String = "app",
    @Field("isEmail") isEmail: String = "true",
    @HeaderMap headers: Map<String, String>
  ): Response<LoginBean>

  @GET("movies/landing")
  suspend fun loadHome(@HeaderMap headers: Map<String, String>): Response<HomeDashboardBean>

  @POST("authentication/refreshToken")
  @FormUrlEncoded
  suspend fun refreshToken(
    @Field("refresh_token") refreshToken: String,
    @HeaderMap headers: Map<String, String>
  ): Response<LoginBean>

  @GET("movies/search")
  suspend fun search(@Query("search") txt: String, @Query("page") page: Int, @HeaderMap headers: Map<String, String>): Response<SearchResponse>

  @GET("users/watchList")
  suspend fun loadFav(
    @Query("page") page: String, @Query("limit") limit: String,
    @HeaderMap headers: Map<String, String>
  ): Response<FavsResponse>

  @POST("users/settings")
  @FormUrlEncoded
  suspend fun updateLang(@Field("language") lang: String, @HeaderMap headers: Map<String, String>): Response<ChangeLangResponse>

  @GET("movies")
  suspend fun getMovieDetails(@Query("id") id: String, @HeaderMap headers: Map<String, String>):
      Response<MovieDetailBean>

  @GET("movies/generateMovieToken")
  suspend fun getMovieAdData(
    @Query("deviceType") deviceType: String = "Android",
    @Query("movieId") movieId: String,
    @HeaderMap headers: Map<String, String>
  ): Response<MovieAdBean>

  @GET("movies/seeAll")
  suspend fun getAll(
    @Query("type") type: String,
    @Query("newRelease") newRelease: String,
    @Query("page") page: String,
    @HeaderMap headers: Map<String, String>
  ): Response<ListingResponse>

  @POST("users/watchList")
  @FormUrlEncoded
  suspend fun addToFav(
    @Field("movieId") movieId: String,
    @HeaderMap headers: Map<String, String>
  ):Response<AddToFavResponse>


}