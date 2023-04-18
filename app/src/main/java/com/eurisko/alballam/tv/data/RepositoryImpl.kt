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
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : Repository {
  override suspend fun login(email: String, password: String) = remoteDataSource.login(email, password)
  override suspend fun refreshToken(): Response<LoginBean> = remoteDataSource.refreshToken()

  override suspend fun loadHomeContent(): Response<HomeDashboardBean> = remoteDataSource.loadHomeContent()
  override suspend fun search(txt: String): Response<SearchResponse> =
    remoteDataSource.search(txt)

  override suspend fun loadFav(): Response<FavsResponse> = remoteDataSource.loadFav()

  override suspend fun changeLang(lang: String): Response<ChangeLangResponse> = remoteDataSource.updateLang(lang)
  override suspend fun getMovieDetails(id: String): Response<MovieDetailBean> =
    remoteDataSource.getMovieDetails(id)

  override suspend fun getMovieExtraDetails(id: String): Response<MovieAdBean> =
    remoteDataSource.getMovieExtraDetails(id)

  override suspend fun listing(type: String, isNewRelease:Boolean): Response<ListingResponse> =
    remoteDataSource.getListing(type, isNewRelease)

  override suspend fun addToFav(id: String): Response<AddToFavResponse> =
    remoteDataSource.addToFav(id)
}