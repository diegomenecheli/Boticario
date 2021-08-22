package com.boticario.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.boticario.ui.login.view.LoginActivity

class SessionManager(context: Context) {

    var PRIVATE_MODE = 0
    private val PREF_NAME = "AndroidHivePref"
    private val IS_LOGIN = "IsLoggedIn"
    val KEY_NAME = "name"
    val KEY_EMAIL = "email"
    private var _context: Context? = context
    private var pref: SharedPreferences? = _context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref!!.edit()

    fun createLoginSession(name: String?, email: String?) {
        editor?.putBoolean(IS_LOGIN, true)
        editor?.putString(KEY_NAME, name)
        editor?.putString(KEY_EMAIL, email)
        editor?.commit()
    }

    fun checkLogin() {
        // Check login status
        if (!isLoggedIn()) {
            val i = Intent(_context, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            _context?.startActivity(i)
        }
    }

    fun getUserDetails(): HashMap<String, String?>? {
        val user = HashMap<String, String?>()
        user[KEY_NAME] = pref!!.getString(KEY_NAME, null)

        user[KEY_EMAIL] = pref!!.getString(KEY_EMAIL, null)

        return user
    }

    fun logoutUser() {
        editor!!.clear()
        editor!!.commit()

        val i = Intent(_context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        _context!!.startActivity(i)
    }

    // Get Login State
    fun isLoggedIn(): Boolean {
        return pref!!.getBoolean(IS_LOGIN, false)
    }
}
