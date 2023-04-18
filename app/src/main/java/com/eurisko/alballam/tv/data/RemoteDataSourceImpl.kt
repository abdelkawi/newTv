package com.eurisko.alballam.tv.data

import com.eurisko.alballam.tv.UserManager
import com.eurisko.alballam.tv.data.model.AddToFavResponse
import com.eurisko.alballam.tv.data.model.ChangeLangResponse
import com.eurisko.alballam.tv.data.model.FavsResponse
import com.eurisko.alballam.tv.data.model.ListingResponse
import com.eurisko.alballam.tv.data.model.LoginBean
import com.eurisko.alballam.tv.data.model.MovieAdBean
import com.eurisko.alballam.tv.data.model.MovieDetailBean

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apis: ApiService, private val userManager: UserManager) : RemoteDataSource {
  override suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Accept-Language"] = "ar"
    headers["content-type"] = "application/x-www-form-urlencoded"
    headers["Accept"] = "application/json"
    apis.login(email, password, headers = headers)
  }


  override suspend fun loadHomeContent() = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    headers["Accept"] = "application/json"
    apis.loadHome(headers)
  }

  override suspend fun search(txt: String) = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.search(txt, 1, headers)
  }

  override suspend fun loadFav(): Response<FavsResponse> = withContext(Dispatchers.IO)
  {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.loadFav("1", "20", headers)
  }

  override suspend fun updateLang(lang: String): Response<ChangeLangResponse> = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.updateLang(lang, headers)
  }

  override suspend fun getMovieDetails(id: String): Response<MovieDetailBean> = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.getMovieDetails(id, headers)
  }

  override suspend fun getMovieExtraDetails(id: String): Response<MovieAdBean> = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.getMovieAdData("Android", id, headers)
  }

  override suspend fun getListing(type: String, isNewRelease: Boolean): Response<ListingResponse> = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.getAll(type, if (isNewRelease) "true" else "false", "1", headers)
  }

  override suspend fun addToFav(id: String): Response<AddToFavResponse> = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.accessToken ?: ""
    headers["Accept-Language"] = userManager.getUserData()?.data?.result?.language ?: "ar"
    apis.addToFav(id, headers)
  }

  override suspend fun refreshToken(): Response<LoginBean> = withContext(Dispatchers.IO) {
    val headers = hashMapOf<String, String>()
    headers["Authorization"] = userManager.getUserData()?.data?.result?.refreshTokenHeader ?: ""
    headers["Accept-Language"] = "ar"
    headers["content-type"] = "application/x-www-form-urlencoded"
    headers["Accept"] = "application/json"
    apis.refreshToken(userManager.getUserData()?.data?.result?.refreshToken ?: "", headers)
  }
}