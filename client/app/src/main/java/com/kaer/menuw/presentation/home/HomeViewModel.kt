package com.kaer.menuw.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.domain.entity.CalendarDate
import timber.log.Timber
import java.time.DayOfWeek
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

        when (LocalDate.of(currentDate.year, currentDate.month, 1).dayOfWeek) {
            DayOfWeek.MONDAY -> setDayStartPosition(1, tempList)
            DayOfWeek.TUESDAY -> setDayStartPosition(2, tempList)
            DayOfWeek.WEDNESDAY -> setDayStartPosition(3, tempList)
            DayOfWeek.THURSDAY -> setDayStartPosition(4, tempList)
            DayOfWeek.FRIDAY -> setDayStartPosition(5, tempList)
            DayOfWeek.SATURDAY -> setDayStartPosition(6, tempList)
            else -> null
        }

        for (i in 1 until  currentDate.lengthOfMonth() + 1) {
            if (i == currentDate.dayOfMonth) {
                tempList.add(CalendarDate(i, true))
            } else {
                tempList.add(CalendarDate(i, false))
            }
        }
        _calendarDate.value = tempList
    }

    private fun setDayStartPosition(position: Int, tempList: ArrayList<CalendarDate>) {
        for (i in 1 until position + 1) {
            tempList.add(CalendarDate(0, false))
        }
    }
}