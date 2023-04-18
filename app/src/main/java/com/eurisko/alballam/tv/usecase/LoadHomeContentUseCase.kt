package com.eurisko.alballam.tv.usecase

import com.eurisko.alballam.tv.data.Repository
import com.eurisko.alballam.tv.data.model.HomeDashboardBean
import retrofit2.Response
import javax.inject.Inject

class LoadHomeContentUseCase @Inject constructor(private val repository: Repository) {

  suspend operator fun invoke() : Response<HomeDashboardBean>{
    return  repository.loadHomeContent()
  }
}