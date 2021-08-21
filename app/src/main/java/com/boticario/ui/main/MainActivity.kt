package com.boticario.ui.main

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.viewbinding.ViewBinding
import com.boticario.R
import com.boticario.databinding.ActivityMainBinding
import com.boticario.ui.AbstractActivity
import com.boticario.ui.news.NewsActivity

class MainActivity : AbstractActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun getLayout(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        toggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    val qrCodeActivity = Intent(applicationContext, MainActivity::class.java)
                    startActivity(qrCodeActivity)
                }
                R.id.nav_search -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    val qrCodeActivity = Intent(applicationContext, NewsActivity::class.java)
                    startActivity(qrCodeActivity)
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}