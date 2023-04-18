package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.MovieDetailBean
import retrofit2.Response
import javax.inject.Inject

class GetMovieDetails @Inject constructor(val repository: Repository) {
  suspend operator fun invoke(id: String): Response<MovieDetailBean> {
    return repository.getMovieDetails(id)
  }
}
