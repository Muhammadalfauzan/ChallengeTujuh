package com.example.challengeempat.sharedpref

import android.content.Context
import android.content.SharedPreferences


class ViewPreference(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    fun saveLayoutPref(isListView: Boolean) {
        prefs.edit().putBoolean(LAYOUT_KEY, isListView).apply()
    }

    fun getLayoutPref(): Boolean {
        return prefs.getBoolean(LAYOUT_KEY, true)
    }

    companion object {
        private const val NAME = "UserPrefs"
        private const val LAYOUT_KEY = "LayoutKey"
    }
}
