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

interface RemoteDataSource {
  suspend fun login(email: String, password: String): Response<LoginBean>
  suspend fun refreshToken(): Response<LoginBean>
  suspend fun loadHomeContent(): Response<HomeDashboardBean>

  suspend fun search(txt: String): Response<SearchResponse>
  suspend fun loadFav(): Response<FavsResponse>
  suspend fun updateLang(lang: String): Response<ChangeLangResponse>
  suspend fun getMovieDetails(id: String): Response<MovieDetailBean>
  suspend fun getMovieExtraDetails(id: String): Response<MovieAdBean>
  suspend fun getListing(type: String, isNewRelease: Boolean): Response<ListingResponse>
  suspend fun addToFav(id: String): Response<AddToFavResponse>
}