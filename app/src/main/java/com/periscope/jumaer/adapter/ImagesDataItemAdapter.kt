package com.periscope.jumaer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.periscope.jumaer.databinding.LayoutImageSliderItemBinding
import com.periscope.jumaer.model.ImageItem
import com.periscope.jumaer.ui_support.DiffCallback

class ImagesDataItemAdapter(
    private val context: Context,
    private var dataList: List<ImageItem>
) : RecyclerView.Adapter<ImagesDataItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayoutImageSliderItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutImageSliderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            image.setImageDrawable(item.image)

            cancelButton.setOnClickListener {
                Toast.makeText(context,"NOTHING SAID",Toast.LENGTH_SHORT).show()
            }

            if(item.isSelected){

            }
            else{

            }
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<ImageItem>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

}