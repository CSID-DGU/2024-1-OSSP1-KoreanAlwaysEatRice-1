package com.kaer.menuw.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentHomeBinding
import com.kaer.menuw.presentation.home.calendar.CalendarAdapter
import com.kaer.menuw.util.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private var _calendarAdapter: CalendarAdapter? = null
    private val calendarAdapter
        get() = requireNotNull(_calendarAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initHomeView()
    }

    private fun initHomeView() {
        viewModel.setCurrentMonth()
        initCalendarView()
    }

    private fun initCalendarView() {
        _calendarAdapter = CalendarAdapter()
        binding.rcvHomeCalendar.adapter = calendarAdapter
        viewModel.setCurrentCalendar()

        viewModel.calendarDate.observe(viewLifecycleOwner) {
            calendarAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _calendarAdapter = null
    }
}