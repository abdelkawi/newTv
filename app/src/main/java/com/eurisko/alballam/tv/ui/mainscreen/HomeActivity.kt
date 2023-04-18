package com.eurisko.alballam.tv.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.leanback.widget.BrowseFrameLayout
import com.eurisko.alballam.tv.Constant
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.R.id
import com.eurisko.alballam.tv.UserManager
import com.eurisko.alballam.tv.ui.exit.ExitApp
import com.eurisko.alballam.tv.ui.fav.FavoriteActivity
import com.eurisko.alballam.tv.ui.search.SearchActivity
import com.eurisko.alballam.tv.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : FragmentActivity(), OnKeyListener {
  private lateinit var searchIv: TextView
  private lateinit var homeIv: TextView
  private lateinit var favoriteIv: TextView
  private lateinit var settings: TextView
  private lateinit var exitAppIv: TextView
  private lateinit var frameContainer: FrameLayout
  private lateinit var menuView: BrowseFrameLayout
  @Inject lateinit var userManager: UserManager
  private var sideMenu = false
  private var selectedMenu = Constant.HOME
  private var lastSelectedMenu: View? = null
  private var currentFragment: Fragment? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.home_activity)
    searchIv = findViewById(id.btn_search)
    homeIv = findViewById(id.btn_home)
    favoriteIv = findViewById(id.btn_fav)
    settings = findViewById(id.btn_settings)
    exitAppIv = findViewById(id.btn_exit_app)
    menuView = findViewById(id.sideMenu)
    frameContainer = findViewById(id.content_container)
    searchIv.setOnKeyListener(this)
    homeIv.setOnKeyListener(this)
    exitAppIv.setOnKeyListener(this)
    settings.setOnKeyListener(this)
    favoriteIv.setOnKeyListener(this)
    switchFragment(HomeFragment())

  }

  fun switchFragment(fragment: Fragment) {
    showFragment(fragment, frameContainer.id, false, true)
  }


  private fun switchToLastSelect() {
    when (selectedMenu) {
      Constant.HOME -> {
        homeIv.requestFocus()
      }
      Constant.SEARCH -> {
        searchIv.requestFocus()
      }
      Constant.FAVORITE -> {
        favoriteIv.requestFocus()
      }
      Constant.SETTINGS -> {
        settings.requestFocus()
      }
      Constant.EXIT_APP -> {
        exitAppIv.requestFocus()
      }

    }
  }

  override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
      if (sideMenu && Locale.getDefault() == Locale.ENGLISH) {
        sideMenu = false
        //      closeMenu()
      }
    }
    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
      if (sideMenu) {
        sideMenu = false
        //       closeMenu()
      }
    }

    return super.onKeyDown(keyCode, event)

  }

  override fun onBackPressed() {
    if (sideMenu) {
      sideMenu = false

    } else {
      if (currentFragment is HomeFragment) {
        switchFragment(ExitApp())
      } else
        super.onBackPressed()
    }
  }

  fun showFragment(
    fragment: Fragment,
    @IdRes container: Int,
    replace: Boolean,
    addReplacedToBAckStack: Boolean = false,
    tag: String = ""
  ) {
    supportFragmentManager.apply {
      beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        try {
          if (replace) {
            replace(container, fragment, tag).commit()
            if (addReplacedToBAckStack) addToBackStack(null)
          } else {
            addToBackStack(null)
            add(container, fragment, tag).commitAllowingStateLoss()
            executePendingTransactions()
          }
        } catch (e: Exception) {

        }
      }
    }
  }

  override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

    if (event?.action == KeyEvent.ACTION_DOWN)
      when (keyCode) {
        KeyEvent.KEYCODE_DPAD_LEFT -> {
          if (!sideMenu) {
            switchToLastSelect()
            //        openMenu()
            sideMenu = true
          }
        }
        KeyEvent.KEYCODE_DPAD_RIGHT -> {
          if (Locale.getDefault() != Locale.ENGLISH) {
            if (!sideMenu) {
              switchToLastSelect()
              //         openMenu()
              sideMenu = true
            }
          }
        }
        KeyEvent.KEYCODE_DPAD_CENTER -> {
          lastSelectedMenu?.isActivated = false
          v?.isActivated = true
          lastSelectedMenu = v!!
          when (v.id) {
            id.btn_home -> {
              selectedMenu = Constant.HOME
              switchFragment(HomeFragment())
            }
            id.btn_settings -> {
              selectedMenu = Constant.SETTINGS
              switchFragment(SettingsFragment())
            }
            id.btn_fav -> {
              selectedMenu = Constant.FAVORITE
              startActivity(Intent(this, FavoriteActivity::class.java))
            }
            id.btn_search -> {
              selectedMenu = Constant.SEARCH
              startActivity(Intent(this, SearchActivity::class.java))
            }
            id.btn_exit_app -> {
              selectedMenu = Constant.EXIT_APP
              switchFragment(ExitApp())
            }
          }
        }
        KeyEvent.KEYCODE_BACK -> {
          onBackPressed()
        }
      }
    else if (event?.action == KeyEvent.ACTION_UP) {
      if (!sideMenu) {
        switchToLastSelect()
        //        openMenu()
        sideMenu = true
      }
    }
    return false
  }


//  private fun openMenu() {
//    val animSlide: Animation = AnimationUtils.loadAnimation(this, anim.slide_in_left)
//    menuView.startAnimation(animSlide)
//    menuView.requestLayout()
//    menuView.layoutParams.width = Common.getWidth(this, 16)
//  }
//
//  private fun closeMenu() {
//    menuView.requestLayout()
//    menuView.layoutParams.width = Common.getWidth(this, 5)
//    frameContainer.requestFocus()
//    sideMenu = false
//  }

}