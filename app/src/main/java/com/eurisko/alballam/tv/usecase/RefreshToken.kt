package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.LoginBean
import retrofit2.Response
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(private val repository: Repository) {
  suspend operator fun invoke(): Response<LoginBean> {
    return repository.refreshToken()
  }
}