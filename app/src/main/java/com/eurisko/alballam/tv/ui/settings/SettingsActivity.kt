package com.eurisko.alballam.tv.ui.settings

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.UserManager
import com.eurisko.alballam.tv.databinding.FragmentSettingsBinding
import com.eurisko.alballam.tv.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : FragmentActivity() {
  @Inject lateinit var userManager: UserManager
  private lateinit var binding: FragmentSettingsBinding
  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = FragmentSettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    if (userManager.getUserData()?.data?.result?.language == "ar") {
      binding.rbSettingsAr.isChecked = true
    } else binding.rbSettingsEn.isChecked = false

    setListener()
  }

  private fun setListener() {
    binding.rbSettingsAr.setOnCheckedChangeListener { buttonView, isChecked ->
      if (isChecked)
        changeLangDialog("ar")
    }
    binding.rbSettingsEn.setOnCheckedChangeListener { buttonView, isChecked ->
      if (isChecked)
        changeLangDialog("en")
    }
  }

  fun update(lang: String) {
    lifecycleScope.launch {
      viewModel.updateLang(lang).collect {
        when (it) {
          is DataState -> {
            Locale.setDefault(Locale(lang))
            val config = Configuration()
            config.setLocale(Locale(lang))
            resources.updateConfiguration(config, resources.displayMetrics)
            recreate()
          }
          is ErrorState -> {

          }
          LoadingState -> {

          }
        }
      }
    }
  }

  private fun changeLangDialog(lang: String) {
    val alretDialog = AlertDialog.Builder(this)
      .setMessage(getString(R.string.msg_confirm_change_language))
      .setPositiveButton(getText(R.string.label_ok)) { dialog, which ->
        update(lang)
      }
      .setNegativeButton(getText(R.string.label_cancel)) { dialog, which ->
        dialog.dismiss()
      }
    alretDialog.show()
  }
}
