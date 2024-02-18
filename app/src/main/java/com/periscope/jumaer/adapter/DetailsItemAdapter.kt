package com.periscope.jumaer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.periscope.jumaer.R
import com.periscope.jumaer.databinding.LayoutItemViewSwipeBinding
import com.periscope.jumaer.model.DetailItem
import com.periscope.jumaer.ui_support.DiffCallback

class DetailsItemAdapter (
    private val context: Context,
    var dataList: List<DetailItem>,
    val onItemClick: (position: Int) -> Unit,
    val onChangeColor: (position: Int, color: Int) -> Unit
) : RecyclerView.Adapter<DetailsItemAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutItemViewSwipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutItemViewSwipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {

            // Set clicks ..
            layoutRoot.setOnClickListener {
                onItemClick(position)
            }

            imgMinimizeButton.setOnClickListener {
                onChangeColor(position, R.color.warning)
                changeColor(viewDynamicColor, R.color.warning)
            }

            imgCorrectButton.setOnClickListener {
                onChangeColor(position, R.color.green)
                changeColor(viewDynamicColor, R.color.green)
            }

            imgBlockButton.setOnClickListener {
                onChangeColor(position, R.color.ash)
                changeColor(viewDynamicColor, R.color.ash)
            }
            imgCancelButton.setOnClickListener {
                onChangeColor(position, R.color.red)
                changeColor(viewDynamicColor, R.color.red)
            }

            txtMain.text = item.hint
            txtComment.text = item.message
            txtLink.text = item.link
            txtStart.text = item.star

        }
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<DetailItem>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    private fun changeColor(viewDynamicColor: View, color: Int) {
        viewDynamicColor.setBackgroundColor(
            ContextCompat.getColor(
                context,
                color
            )
        )
    }
}