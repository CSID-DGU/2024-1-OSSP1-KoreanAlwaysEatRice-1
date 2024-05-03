package com.kaer.menuw.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.domain.entity.CalendarDate
import timber.log.Timber
import java.time.LocalDate

class HomeViewModel: ViewModel() {

    private val _calendarDate: MutableLiveData<ArrayList<CalendarDate>> = MutableLiveData()
    val calendarDate: LiveData<ArrayList<CalendarDate>>
        get() = _calendarDate

    private val currentDate = LocalDate.now().dayOfMonth

    fun setCurrentDate() {
        val tempList: ArrayList<CalendarDate> = ArrayList()
        for (i in 1 until  32) {
            tempList.add(CalendarDate(i))
        }
        _calendarDate.value = tempList
    }
}