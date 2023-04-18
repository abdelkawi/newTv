package com.eurisko.alballam.tv.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.UserManager
import com.eurisko.alballam.tv.ui.mainscreen.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity : FragmentActivity() {
  @Inject lateinit var userManager: UserManager
  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    if (userManager.getUserData() != null &&
      userManager.getUserData()?.data != null
      && userManager.getUserData()?.data?.result != null
    ) {
      lifecycleScope.launch {
        viewModel.refreshToken().collect {
          when (it) {
            is DataState -> {
              if (it.data.body() != null && it.data.body()?.data?.result?.accessToken != "") {
                userManager.saveUserData(it.data.body()!!)
                finish()
                startActivity(Intent(this@StartActivity, HomeActivity::class.java))
              } else {
                finish()
                startActivity(Intent(this@StartActivity, SignInActivity::class.java))
              }
            }
            is ErrorState -> {
              finish()
              startActivity(Intent(this@StartActivity, SignInActivity::class.java))
            }
            LoadingState -> {

            }
          }
        }

      }
    } else {

      startActivity(Intent(this, SignInActivity::class.java))
    }

  }
}