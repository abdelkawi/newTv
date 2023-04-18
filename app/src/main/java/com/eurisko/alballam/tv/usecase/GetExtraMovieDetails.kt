package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.MovieAdBean
import retrofit2.Response
import javax.inject.Inject

class GetExtraMovieDetails @Inject constructor(val repository: Repository) {
  suspend operator fun invoke(id: String): Response<MovieAdBean> {
    return repository.getMovieExtraDetails(id)
  }
}