package com.kaer.menuw.presentation.home.refrigerator.add

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kaer.menuw.domain.entity.RefrigeratorIngredientItem

class SharedPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun storeIngredientIdList(idList: ArrayList<RefrigeratorIngredientItem>) {
        val idListJson = Gson().toJson(idList)
        with(sharedPreferences.edit()) {
            putString(REFRIGERATOR_KEY_NAME, idListJson)
            apply()
        }
    }

    fun getIngredientList(): ArrayList<RefrigeratorIngredientItem> {
        val idListJson = sharedPreferences.getString(REFRIGERATOR_KEY_NAME, null)
        return if (idListJson != null) {
            val type = object : TypeToken<ArrayList<RefrigeratorIngredientItem>>() {}.type
            Gson().fromJson(idListJson, type)
        } else {
            ArrayList()
        }
    }

    fun removeIngredientItem(itemToRemove: RefrigeratorIngredientItem) {
        val currentList = getIngredientList()
        val updatedList = ArrayList(currentList.filter { it != itemToRemove })
        storeIngredientIdList(updatedList)
    }

    companion object {
        const val PREFERENCE_NAME = "APP_PREFERENCES"
        const val REFRIGERATOR_KEY_NAME = "STORED_REFRIGERATOR"
    }
}