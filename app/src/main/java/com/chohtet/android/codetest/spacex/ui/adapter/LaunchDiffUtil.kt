package com.chohtet.android.codetest.spacex.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.codetest.domain.model.Launch
import com.chohtet.android.codetest.spacex.GetLaunchListQuery

class LaunchDiffUtil : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(
        oldItem: Launch,
        newItem: Launch
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Launch,
        newItem: Launch
    ): Boolean {
        return oldItem == newItem
    }
}