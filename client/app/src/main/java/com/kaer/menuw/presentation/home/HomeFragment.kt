package com.kaer.menuw.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kaer.menuw.R
import com.kaer.menuw.databinding.FragmentHomeBinding
import com.kaer.menuw.presentation.home.calendar.CalendarAdapter
import com.kaer.menuw.util.base.BaseDialog
import com.kaer.menuw.util.base.BaseDialog.Companion.DIALOG
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
        clickCalendarView()
        clickWeatherView()
        clickRecommendView()
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

    private fun clickCalendarView() {
        binding.layoutCalendar.setOnClickListener {
            BaseDialog.Builder().build(
                title = "해당 기능은 업데이트 예정이에요!",
                content = "이전에 기록했던 메뉴를 달력에서 확인해 보세요!",
                btnAction = {}
            ).show(parentFragmentManager, DIALOG)
        }
    }

    private fun clickWeatherView() {
        binding.layoutWeather.setOnClickListener {
            BaseDialog.Builder().build(
                title = "해당 기능은 업데이트 예정이에요!",
                content = "오늘 날씨에 어울리는 메뉴를 추천받아 보세요!",
                btnAction = {}
            ).show(parentFragmentManager, DIALOG)
        }
    }

    private fun clickRecommendView() {
        binding.layoutRecommend.setOnClickListener {
            BaseDialog.Builder().build(
                title = "해당 기능은 업데이트 예정이에요!",
                content = "나에게 맞는 조리법을 추천받아 보세요!",
                btnAction = {}
            ).show(parentFragmentManager, DIALOG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _calendarAdapter = null
    }
}