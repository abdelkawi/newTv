package com.eurisko.alballam.tv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurisko.alballam.tv.State
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.Utils
import com.eurisko.alballam.tv.data.model.HomeDashboardBean
import com.eurisko.alballam.tv.usecase.AddToFavUseCase
import com.eurisko.alballam.tv.usecase.ChangeLangUseCase
import com.eurisko.alballam.tv.usecase.FavsUseCase
import com.eurisko.alballam.tv.usecase.GetExtraMovieDetails
import com.eurisko.alballam.tv.usecase.GetMovieDetails
import com.eurisko.alballam.tv.usecase.ListingUseCase
import com.eurisko.alballam.tv.usecase.LoadHomeContentUseCase
import com.eurisko.alballam.tv.usecase.LoginUseCase
import com.eurisko.alballam.tv.usecase.RefreshTokenUseCase
import com.eurisko.alballam.tv.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  val loginUseCase: LoginUseCase,
  val loadHomeContentUseCase: LoadHomeContentUseCase,
  val searchUseCase: SearchUseCase,
  val refreshTokenUseCase: RefreshTokenUseCase,
  val favsUseCase: FavsUseCase,
  val changeLangUseCase: ChangeLangUseCase,
  val getMovieDetails: GetMovieDetails,
  val getMovieExtraDetails: GetExtraMovieDetails,
  val listingUseCase: ListingUseCase,
  val addToFavUseCase: AddToFavUseCase
) : ViewModel() {
  private val _homeData = MutableLiveData<State<HomeDashboardBean>>()
  val homeData: LiveData<State<HomeDashboardBean>> get() = _homeData
  fun login(userEmail: String, password: String) = flow {
    emit(LoadingState)
    try {
      emit(DataState(loginUseCase(userEmail, password)))
    } catch (e: Exception) {
      e.printStackTrace()
      emit(Utils.resolveError(e))
    }
  }

  fun search(txt: String) = flow {
    emit(LoadingState)
    try {
      emit(DataState(searchUseCase(txt)))
    } catch (e: Exception) {
      e.printStackTrace()
      emit(Utils.resolveError(e))
    }
  }

  fun loadFavs() = flow {
    emit(LoadingState)
    emit(favsUseCase())
  }

  fun loadHomeContent() {
    viewModelScope.launch {
      _homeData.value = LoadingState
      val res = loadHomeContentUseCase()
      if (res.body() != null)
        _homeData.value = DataState(res.body()!!)
      else _homeData.value = ErrorState(res.message())
    }
  }

  fun refreshToken() = flow {
    emit(LoadingState)
    try {
      emit(DataState(refreshTokenUseCase()))
    } catch (e: Exception) {
      e.printStackTrace()
      emit(Utils.resolveError(e))
    }
  }

  fun updateLang(lang: String) = flow {
    emit(LoadingState)
    try {
      emit(DataState(changeLangUseCase(lang)))
    } catch (e: Exception) {
      e.printStackTrace()
      emit(Utils.resolveError(e))
    }
  }

  fun getMovie(id: String) = flow {
    emit(LoadingState)
    try {
      emit(DataState(getMovieDetails(id)))
    } catch (e: Exception) {
      e.printStackTrace()
      emit(Utils.resolveError(e))
    }
  }

  fun getMovieExtraDetail(id: String) = flow {
    emit(LoadingState)
    try {
      emit(DataState(getMovieExtraDetails(id)))
    } catch (e: Exception) {
      e.printStackTrace()
      emit(Utils.resolveError(e))
    }
  }

  fun getItems(type: String, isNewRelease: Boolean) = flow {
    emit(listingUseCase(type, isNewRelease))
  }

  fun addToFav(id: String) = flow {
    emit(addToFavUseCase(id))
  }
}