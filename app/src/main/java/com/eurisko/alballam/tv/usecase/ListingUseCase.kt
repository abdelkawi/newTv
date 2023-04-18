package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.State
import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.ListingResponse
import javax.inject.Inject

class ListingUseCase @Inject constructor(val repository: Repository) {

  suspend operator fun invoke(type: String, isNewRelease: Boolean): State<ListingResponse> {
    val res = repository.listing(type, isNewRelease)
    return if (res.body()?.data != null) {
      State.DataState(res.body()!!)
    } else
      State.ErrorState(res.message())
  }
}