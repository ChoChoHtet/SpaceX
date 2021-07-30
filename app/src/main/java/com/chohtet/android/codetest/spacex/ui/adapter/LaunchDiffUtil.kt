package com.chohtet.android.codetest.spacex.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chohtet.android.codetest.spacex.GetLaunchListQuery

class LaunchDiffUtil : DiffUtil.ItemCallback<GetLaunchListQuery.Launch>() {
    override fun areItemsTheSame(
        oldItem: GetLaunchListQuery.Launch,
        newItem: GetLaunchListQuery.Launch
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GetLaunchListQuery.Launch,
        newItem: GetLaunchListQuery.Launch
    ): Boolean {
        return oldItem == newItem
    }
}