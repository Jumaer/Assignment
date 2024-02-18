package com.periscope.jumaer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.periscope.jumaer.R
import com.periscope.jumaer.databinding.LayoutDotItemViewBinding
import com.periscope.jumaer.model.DotColor
import com.periscope.jumaer.ui_support.DiffCallback

class DotItemAdapter    (
    private val context: Context,
    private var dataList: List<DotColor>
) : RecyclerView.Adapter<DotItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayoutDotItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutDotItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            if(-1 == item.colorPosition){
                dotImg.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }
            else dotImg.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<DotColor>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

}