package com.boticario.ui.main

import android.content.Intent
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.viewbinding.ViewBinding
import com.boticario.R
import com.boticario.databinding.ActivityMainBinding
import com.boticario.databinding.NavHeaderBinding
import com.boticario.ui.AbstractActivity
import com.boticario.ui.news.NewsActivity
import com.boticario.utils.SessionManager


class MainActivity : AbstractActivity() {

    private lateinit var session: SessionManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingHeader: NavHeaderBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun getLayout(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        bindingHeader = NavHeaderBinding.inflate(layoutInflater)
        session = SessionManager(this)
        toggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadUsersInformation()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_search -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    val qrCodeActivity = Intent(applicationContext, NewsActivity::class.java)
                    startActivity(qrCodeActivity)
                }
                R.id.nav_logoff -> {
                    session.logoutUser()
                }
            }
            true
        }
    }

    private fun loadUsersInformation() {
        val user = session.getUserDetails()
        bindingHeader.nameHeader.text = user!![session.KEY_NAME]
        Log.d("xuxa", "loadUsersInformation: " + user!![session.KEY_NAME])
        bindingHeader.usernameHeader.text = user[session.KEY_EMAIL]
        Log.d("xuxa", "loadUsersInformation: " + user!![session.KEY_EMAIL])

        bindingHeader.usernameHeader.text
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}