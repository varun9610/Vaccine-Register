package com.android.vaccineregister

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager @SuppressLint("CommitPrefEdits")
constructor(context: Context) {

    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences(PREFERENCE_CONFIGURATION_NAME, PRIVATE_MODE)
        editor = preferences.edit()
    }

    fun isFirstRun() = preferences.getBoolean(FIRST_TIME, true)

    fun setFirstRun() {
        editor.putBoolean(FIRST_TIME, false).commit()
        editor.commit()
    }

    fun isLoggedIn() = preferences.getBoolean(LOGGED_IN, true)

    fun setLoggedIn() {
        editor.putBoolean(LOGGED_IN, false).commit()
        editor.commit()
    }


    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREFERENCE_CONFIGURATION_NAME = "configuration"
        private const val FIRST_TIME = "isFirstRun"
        private const val LOGGED_IN = "isFirstRun"
    }
}