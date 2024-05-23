package com.kaer.menuw.presentation.refrigerator.add

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kaer.menuw.domain.entity.IngredientTotal

class SharedPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun storeIngredientIdList(idList: ArrayList<IngredientTotal.IngredientItem>) {
        val idListJson = Gson().toJson(idList)
        with (sharedPreferences.edit()) {
            putString(KEY_NAME, idListJson)
            apply()
        }
    }

    fun getIngredientList(): ArrayList<IngredientTotal.IngredientItem> {
        val idListJson = sharedPreferences.getString(KEY_NAME, null)
        return if (idListJson != null) {
            val type = object : TypeToken<ArrayList<IngredientTotal.IngredientItem>>() {}.type
            Gson().fromJson(idListJson, type)
        } else {
            ArrayList()
        }
    }

    fun removeIngredientItem(itemToRemove: IngredientTotal.IngredientItem) {
        val currentList = getIngredientList()
        val updatedList = ArrayList(currentList.filter { it != itemToRemove })
        storeIngredientIdList(updatedList)
    }

    companion object {
        const val PREFERENCE_NAME = "APP_PREFERENCES"
        const val KEY_NAME = "STORED_INGREDIENT"
    }
}