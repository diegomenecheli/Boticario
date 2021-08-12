package com.boticario.ui.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.boticario.databinding.ActivityLoginBinding
import com.boticario.databinding.ActivityNewsBinding
import com.boticario.ui.AbstractActivity

class LoginActivity : AbstractActivity(){
    private lateinit var binding: ActivityLoginBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding
    }
    override fun onInject() {

    }
}