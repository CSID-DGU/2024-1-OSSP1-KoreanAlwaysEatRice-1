package com.kaer.menuw.presentation.home.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaer.menuw.databinding.ItemCalendarDateBinding
import com.kaer.menuw.domain.entity.CalendarDate
import com.kaer.menuw.util.ItemDiffCallback

class CalendarAdapter:
    ListAdapter<CalendarDate, CalendarAdapter.CalendarViewHolder>(
        ItemDiffCallback<CalendarDate> (
            onContentsTheSame = {old, new -> old == new},
            onItemsTheSame = {old, new -> old.date == new.date}
        )
    ) {

    inner class CalendarViewHolder(
        private val binding: ItemCalendarDateBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CalendarDate) {
            binding.item = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}