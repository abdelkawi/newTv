package com.eurisko.alballam.tv.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.UserManager
import com.eurisko.alballam.tv.databinding.ActivitySignInBinding
import com.eurisko.alballam.tv.ui.mainscreen.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity(), OnClickListener {
  lateinit var binding: ActivitySignInBinding
  private val viewModel: MainViewModel by viewModels()
  @Inject lateinit var userManager: UserManager
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_in)
    binding = ActivitySignInBinding.inflate(layoutInflater)
    binding.tvSignIn.setOnClickListener(this)

    binding.tvSignIn.setOnKeyListener { v, keyCode, event ->
      Log.d("Location", "setOnKeyListener")
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER
        || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER
      ) {
        login()
      }
      false
    }
  }

  override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
    val currentF = currentFocus
    Log.d("Location", "onKeyDown")
    if (binding.tvSignIn.id == currentF?.id) {
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
        login()
      }
    }
    return super.onKeyDown(keyCode, event)
  }

  override fun dispatchKeyEvent(event: KeyEvent?): Boolean {

    val currentF = currentFocus
    Log.d("Location", "dispatchKeyEvent ${event.toString()}")
    if (binding.tvSignIn.id == currentF?.id) {
      if (event?.keyCode == KeyEvent.KEYCODE_DPAD_CENTER || event?.keyCode == KEYCODE_ENTER) {
        login()
      }
    }
    return super.dispatchKeyEvent(event)
  }

  fun login() {
    lifecycleScope.launch {
      viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString()).collect() {
        when (it) {
          is DataState -> {
            Log.d("dataFromApi", it.data.body().toString())
            if (it.data.body() != null && it.data.body()?.data?.result?.accessToken != "") {
              userManager.saveUserData(it.data.body()!!)
              startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            } else {
              Toast.makeText(this@SignInActivity, it.data.errorBody().toString(), Toast.LENGTH_LONG).show()
              binding.progressBar.visibility = View.GONE
              binding.tvSignIn.visibility = View.VISIBLE
            }
          }
          is ErrorState -> {
            Toast.makeText(this@SignInActivity, it.exception, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
            binding.tvSignIn.visibility = View.VISIBLE
          }
          LoadingState -> {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvSignIn.visibility = View.GONE

          }
        }
      }
    }

  }

  override fun onClick(v: View?) {
    when (v?.id) {
      binding.tvSignIn.id ->{
        login()
      }
    }
  }

}