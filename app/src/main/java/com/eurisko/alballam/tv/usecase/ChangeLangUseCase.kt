package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.ChangeLangResponse
import retrofit2.Response
import javax.inject.Inject

class ChangeLangUseCase @Inject constructor(val repository: Repository) {
  suspend operator fun invoke(lang: String): Response<ChangeLangResponse> {
    return repository.changeLang(lang)
  }
}