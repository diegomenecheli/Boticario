package com.boticario.ui.main

import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.boticario.R
import com.boticario.adapter.PostAdapter
import com.boticario.databinding.ActivityMainBinding
import com.boticario.databinding.NavHeaderBinding
import com.boticario.model.posts.PostsResponseItem
import com.boticario.model.posts.data.PostDataSource
import com.boticario.presenter.ViewHome
import com.boticario.presenter.post.PostPresenter
import com.boticario.ui.AbstractActivity
import com.boticario.ui.news.NewsActivity
import com.boticario.utils.SessionManager


class MainActivity : AbstractActivity(), ViewHome.PostView {

    private val postAdapter by lazy {
        PostAdapter()
    }

    private lateinit var session: SessionManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingHeader: NavHeaderBinding
    private lateinit var presenter: PostPresenter
    lateinit var toggle: ActionBarDrawerToggle
    override fun getLayout(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        configMenu()
        val dataSource = PostDataSource(this)
        presenter = PostPresenter(this, dataSource)
        presenter.getAll()
        configRecycler()
    }

    fun configMenu(){
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

    private fun configRecycler() {
        with(binding.recycler) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun loadUsersInformation() {

        val user = session.getUserDetails()

        bindingHeader.usernameHeader
        bindingHeader.nameHeader.text = user!![session.KEY_NAME]
        bindingHeader.usernameHeader.text = user[session.KEY_EMAIL]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun registerPost(post: PostsResponseItem) {

    }

    override fun getAll(post: List<PostsResponseItem>) {
        postAdapter.differ.submitList(post)
    }

    override fun hideProgressBar() {
        binding.loading.visibility = View.GONE
    }

    override fun showProgressBar() {
        binding.loading.visibility = View.VISIBLE
    }
}