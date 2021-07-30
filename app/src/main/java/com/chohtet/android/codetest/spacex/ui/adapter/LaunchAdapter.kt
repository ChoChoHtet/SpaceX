package com.chohtet.android.codetest.spacex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.R
import com.chohtet.android.codetest.spacex.databinding.ItemLaunchBinding


class LaunchAdapter(private val listener: ClickListener) : ListAdapter<
        GetLaunchListQuery.Launch, LaunchAdapter.LaunchViewHolder>(LaunchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = DataBindingUtil.inflate<ItemLaunchBinding>(
            LayoutInflater.from(parent.context), R.layout.item_launch, parent, false
        )
        return LaunchViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LaunchViewHolder(
        private val binding: ItemLaunchBinding,
        private val clickListener: ClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: GetLaunchListQuery.Launch) {
            binding.launch = items
            binding.root.setOnClickListener {
                clickListener.onItemClick(items)
            }
        }
    }

    interface ClickListener {
        fun onItemClick(item: GetLaunchListQuery.Launch)
    }

}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: AppCompatImageView, shipUrl: List<GetLaunchListQuery.Ship>) {
    if (!shipUrl.isNullOrEmpty() && !shipUrl[0].image.isNullOrEmpty()) {
        imageView.load(shipUrl[0].image) {
            crossfade(true)
            placeholder(R.drawable.ic_space)
        }
    } else {
        imageView.load(R.drawable.ic_space) {
            crossfade(true)
        }
    }

}

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}