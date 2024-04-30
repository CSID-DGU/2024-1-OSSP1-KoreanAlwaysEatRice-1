package com.kaer.menuw.presentation.refrigerator.add

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("APPPreferences", Context.MODE_PRIVATE)

    fun storeIngredientIdList(idList: ArrayList<Int>) {
        val idListJson = Gson().toJson(idList)
        with (sharedPreferences.edit()) {
            putString("STORED_INGREDIENT", idListJson)
            apply()
        }
    }

    fun getIngredientList(): ArrayList<Int> {
        val idListJson = sharedPreferences.getString("STORED_INGREDIENT", null)
        return if (idListJson != null) {
            val type = object : TypeToken<ArrayList<Int>>() {}.type
            Gson().fromJson(idListJson, type)
        } else {
            ArrayList()
        }
    }
}