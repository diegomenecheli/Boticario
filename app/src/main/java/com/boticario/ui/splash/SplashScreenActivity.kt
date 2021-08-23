package com.boticario.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.boticario.databinding.ActivitySplashScreenBinding
import com.boticario.ui.AbstractActivity
import com.boticario.ui.login.view.LoginActivity
import com.boticario.ui.news.NewsActivity

class SplashScreenActivity : AbstractActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun getLayout(): ViewBinding {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        splashScreen()
    }

    private fun splashScreen() {
        binding.ivSplash.alpha = 0f
        binding.ivSplash.animate().setDuration(1400).alpha(1f).withEndAction {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}