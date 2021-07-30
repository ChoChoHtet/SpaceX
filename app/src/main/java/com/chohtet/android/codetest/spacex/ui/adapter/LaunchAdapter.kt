package com.chohtet.android.codetest.spacex.ui.adapter

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
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
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.load(shipUrl[0].image) {
            crossfade(false)
            placeholder(R.drawable.ic_space)
        }
    } else {
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.load(R.drawable.ic_space) {
            crossfade(true)
        }
    }

}

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("hyperLinkText")
fun setHyperLink(textView: AppCompatTextView, url: String?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        textView.text = if (!url.isNullOrEmpty()) {
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.setLinkTextColor(Color.BLUE)
            Html.fromHtml(url, HtmlCompat.FROM_HTML_MODE_COMPACT)
        } else ""
    } else {
        textView.text = if (!url.isNullOrEmpty()) {
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.setLinkTextColor(Color.BLUE)
            Html.fromHtml(url)
        } else ""
    }


}