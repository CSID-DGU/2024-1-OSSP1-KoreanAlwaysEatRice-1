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

    private val currentDate = LocalDate.now()

    private val _currentMonth: MutableLiveData<String> = MutableLiveData()
    val currentMonth: LiveData<String>
        get() = _currentMonth

    fun setCurrentMonth() {
        _currentMonth.value = "${currentDate.year}년 ${currentDate.monthValue}월"
    }

    fun setCurrentCalendar() {
        val tempList: ArrayList<CalendarDate> = ArrayList()
        for (i in 1 until  32) {
            if (i == currentDate.dayOfMonth) {
                tempList.add(CalendarDate(i, true))
            } else {
                tempList.add(CalendarDate(i, false))
            }
        }
        _calendarDate.value = tempList
    }
}