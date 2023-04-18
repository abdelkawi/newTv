package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.State
import com.eurisko.alballam.tv.data.Repository
import javax.inject.Inject

class AddToFavUseCase @Inject constructor(val repository: Repository) {
  suspend operator fun invoke(id: String): State<String> {
    val res = repository.addToFav(id)
    return if (res.code() == 200)
      State.DataState("Success")
    else State.ErrorState("Error")
  }
}
