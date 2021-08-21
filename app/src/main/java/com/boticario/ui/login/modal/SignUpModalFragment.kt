package com.boticario.ui.login.modal

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.boticario.databinding.FragmentSignUpModalBinding
import com.boticario.model.login.Login
import com.boticario.model.login.data.LoginDataSource
import com.boticario.presenter.ViewHome
import com.boticario.presenter.signup.SignUpPresenter
import com.boticario.ui.AbstractModalFragment
import com.boticario.ui.main.MainActivity
import com.boticario.ui.news.NewsActivity

class SignUpModalFragment : AbstractModalFragment(), ViewHome.SignUpView {

    private lateinit var binding: FragmentSignUpModalBinding
    private lateinit var presenter: SignUpPresenter
    override fun getLayout(): ViewBinding {
        binding = FragmentSignUpModalBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        val dataSource = LoginDataSource(
            requireContext()
        )
        presenter = SignUpPresenter(this, dataSource)
        binding.btnSignUp.setOnClickListener {
            val login =
                Login(
                    binding.username.text.toString(),
                    binding.password.text.toString(),
                    binding.name.text.toString()
                )
            presenter.registerUser(login)
        }
    }

    override fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun register(user: Login) {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.putExtra("login", user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.action = Intent.ACTION_MAIN
        startActivity(intent)
    }


    override fun hideProgressBar() {
        binding.loading.visibility = View.INVISIBLE
    }

    override fun showProgressBar() {
        binding.loading.visibility = View.VISIBLE
    }


}