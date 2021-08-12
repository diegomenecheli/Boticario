package com.boticario.ui.login.modal


import androidx.viewbinding.ViewBinding
import com.boticario.databinding.FragmentLoginModalBinding
import com.boticario.ui.AbstractModalFragment

class LoginModalFragment : AbstractModalFragment() {

    private lateinit var binding: FragmentLoginModalBinding

    override fun getLayout(): ViewBinding {
        binding = FragmentLoginModalBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
    }


}