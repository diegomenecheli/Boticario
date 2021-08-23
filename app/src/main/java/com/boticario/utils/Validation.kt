package com.boticario.utils

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.boticario.R

class Validation {
    companion object{
        // validating password with retype password
        fun isValidPassword(context: Context, pass: String?): Boolean {
            if (pass != null && pass.length > 5) {
                return true
            }
            if (pass == null || pass.isEmpty() || pass.trim { it <= ' ' } == "") {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.validation_no_password),
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_invalid_password),
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        // validating name
        fun isValidUserName(context: Context, name: String?): Boolean {
            if (name != null && name.trim { it <= ' ' } != "") {
                return true
            }
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_no_name),
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        //validation email
        fun isValidEmail(context: Context, email: String?): Boolean {
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.validation_no_email),
                    Toast.LENGTH_LONG
                ).show()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.validation_invalid_email),
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}