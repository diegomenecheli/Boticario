package com.boticario.ui.login.view

import androidx.viewbinding.ViewBinding
import com.boticario.databinding.ActivityLoginBinding
import com.boticario.ui.AbstractActivity
import com.boticario.ui.login.modal.LoginModalFragment

class LoginActivity : AbstractActivity(){
    private lateinit var binding: ActivityLoginBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding
    }
    override fun onInject() {
        binding.btnSignUp.setOnClickListener {
            var loginModalFragment = LoginModalFragment()
            loginModalFragment.show(supportFragmentManager, "")
        }
    }
}