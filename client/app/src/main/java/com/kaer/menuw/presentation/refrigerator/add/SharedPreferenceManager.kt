package com.kaer.menuw.presentation.refrigerator.add

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun storeIngredientIdList(idList: ArrayList<Int>) {
        val idListJson = Gson().toJson(idList)
        with (sharedPreferences.edit()) {
            putString(KEY_NAME, idListJson)
            apply()
        }
    }

    fun getIngredientList(): ArrayList<Int> {
        val idListJson = sharedPreferences.getString(KEY_NAME, null)
        return if (idListJson != null) {
            val type = object : TypeToken<ArrayList<Int>>() {}.type
            Gson().fromJson(idListJson, type)
        } else {
            ArrayList()
        }
    }

    companion object {
        const val PREFERENCE_NAME = "APPPreferences"
        const val KEY_NAME = "STORED_INGREDIENT"
    }
}