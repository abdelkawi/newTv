package com.eurisko.alballam.tv.ui.settings

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
class SettingsFragment : Fragment(R.layout.fragment_settings) {
  @Inject lateinit var userManager: UserManager
  private lateinit var binding: FragmentSettingsBinding
  private val viewModel: MainViewModel by viewModels()
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentSettingsBinding.bind(view)
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
            requireContext().resources.updateConfiguration(config, requireActivity().resources.displayMetrics)
            requireActivity().recreate()
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
    val alretDialog = AlertDialog.Builder(requireContext())
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
