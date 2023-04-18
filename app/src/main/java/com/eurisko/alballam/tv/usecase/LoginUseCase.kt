package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.LoginBean
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {
  suspend operator fun invoke(email: String, password: String): Response<LoginBean> =
    repository.login(email, password)
}