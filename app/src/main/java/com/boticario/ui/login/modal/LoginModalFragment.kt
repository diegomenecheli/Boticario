package com.boticario.ui.login.modal


import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.boticario.databinding.FragmentLoginModalBinding
import com.boticario.model.login.Login
import com.boticario.model.login.data.LoginDataSource
import com.boticario.presenter.ViewHome
import com.boticario.presenter.login.LoginPresenter
import com.boticario.ui.AbstractModalFragment
import com.boticario.ui.news.NewsActivity

class LoginModalFragment : AbstractModalFragment(), ViewHome.LoginView {

    private lateinit var binding: FragmentLoginModalBinding
    private lateinit var presenter: LoginPresenter
    override fun getLayout(): ViewBinding {
        binding = FragmentLoginModalBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        val dataSource = LoginDataSource(
            requireContext()
        )
        presenter = LoginPresenter(this, dataSource)
        binding.btnSignUp.setOnClickListener {
            presenter.validateUser(
                binding.name.text.toString(),
                binding.password.text.toString()
            )
        }
    }

    override fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun saveLogin(login: Login) {
        val intent = Intent(requireContext(), NewsActivity::class.java)
        intent.putExtra("login", login)
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