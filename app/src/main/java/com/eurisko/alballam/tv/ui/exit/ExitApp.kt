package com.eurisko.alballam.tv.ui.exit

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.databinding.ExitAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExitApp : Fragment(R.layout.exit_app), OnKeyListener {
  lateinit var binding: ExitAppBinding
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = ExitAppBinding.bind(view)
    binding.canel.requestFocus()
    binding.canel.setOnKeyListener(this)
    binding.ok.setOnKeyListener(this)

  }

  override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
      if (v?.id == binding.canel.id) {
        requireActivity().onBackPressed()
      } else if (v?.id == binding.ok.id) {
        finishAffinity(requireActivity())
      }
    }
    return false
  }
}