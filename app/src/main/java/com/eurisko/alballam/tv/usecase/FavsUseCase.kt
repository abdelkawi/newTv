package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.State
import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.FavsResponse
import javax.inject.Inject

class FavsUseCase @Inject constructor(val repository: Repository) {
  suspend operator fun invoke(): State<FavsResponse> {
    val res = repository.loadFav()
    return if (res.body() != null && res.body()?.data != null) {
      State.DataState(res.body()!!)
    } else State.ErrorState(res.message())
  }
}