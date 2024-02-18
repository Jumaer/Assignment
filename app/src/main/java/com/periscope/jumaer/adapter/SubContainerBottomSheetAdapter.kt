package com.periscope.jumaer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.periscope.jumaer.databinding.LayoutRecyclerViewSectionMainBinding
import com.periscope.jumaer.model.DetailItem
import com.periscope.jumaer.model.SubSection
import com.periscope.jumaer.ui_support.DiffCallback
import com.periscope.jumaer.ui_support.setItemTouchHelper

class SubContainerBottomSheetAdapter(
    private val context: Context,
    private var dataList: List<SubSection>,
    val onItemClick : (position : Int)->Unit
) : RecyclerView.Adapter<SubContainerBottomSheetAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayoutRecyclerViewSectionMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutRecyclerViewSectionMainBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        val listOfAll = item.dataList
        var mainAdapter: DetailsItemAdapter? = null
        holder.binding.apply {
            txtMain.text = item.mainText
            if(position == 1 || position == 3){
                layoutTopShape.alpha = .5f
            }
            else{
                layoutTopShape.alpha = .7f
            }
            imgArrow.setOnClickListener{
                if(rvSub.isVisible){
                    rvSub.visibility = View.GONE
                } else rvSub.visibility = View.VISIBLE
            }

            mainAdapter =  DetailsItemAdapter(context, listOfAll,
                {
                 onItemClick(it)
                },
                { position, color ->
                    val items = listOfAll[position]
                    items.color = color
                    listOfAll.update(items)

                    mainAdapter!!.updateList(listOfAll.toMutableList())
                }
            )



            rvSub.apply {
                adapter = mainAdapter
                setHasFixedSize(true)
                visibility = View.GONE
            }
            setItemTouchHelper(context,rvSub,mainAdapter!!)


        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<SubSection>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }


    fun List<DetailItem>.update(item: DetailItem): List<DetailItem> {
        val itemIndex = indexOf(item)
        return if (itemIndex == -1) this.toList()
        else slice(0 until itemIndex) + item + slice(itemIndex+1 until size)
    }
}