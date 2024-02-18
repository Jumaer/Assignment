package com.periscope.jumaer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.periscope.jumaer.databinding.LayoutMainPageItemViewBinding
import com.periscope.jumaer.model.InspectionItem
import com.periscope.jumaer.ui_support.DiffCallback

class InspectionItemAdapter (
    private val context: Context,
    private var dataList: List<InspectionItem>,
    val onItemClick: (position: Int) -> Unit,
) : RecyclerView.Adapter<InspectionItemAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutMainPageItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutMainPageItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
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

            txtMain.text = item.mainText
            txtHint.text = item.hint
            txtDate.text = item.date

        }
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<InspectionItem>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }



}