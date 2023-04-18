package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.model.SearchResponse
import com.eurisko.alballam.tv.data.Repository
import retrofit2.Response
import javax.inject.Inject

class SearchUseCase @Inject constructor(val repository: Repository) {
  suspend operator fun invoke(txt: String): Response<SearchResponse> = repository.search(txt)
}