package com.periscope.jumaer.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.periscope.jumaer.R
import com.periscope.jumaer.adapter.DotItemAdapter
import com.periscope.jumaer.adapter.ImagesDataItemAdapter
import com.periscope.jumaer.databinding.FragmentImageSliderBinding
import com.periscope.jumaer.databinding.FragmentLauncherBinding
import com.periscope.jumaer.model.DotColor
import com.periscope.jumaer.model.ImageItem


class ImageSliderFragment : Fragment() {
    private lateinit var binding: FragmentImageSliderBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageSliderBinding.inflate(inflater, container, false)


        binding.rvMain.apply {
            adapter = ImagesDataItemAdapter(requireContext(),getImageList())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    onScrolledView(recyclerView, newState)
                }

            })
            setHasFixedSize(true)
        }

        dotAdapter = DotItemAdapter(requireContext(),getColorList(0))

        binding.rvDot.apply {
            adapter = dotAdapter
            setHasFixedSize(true)
        }


        return binding.root
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getImageList(): List<ImageItem> {
        return mutableListOf(
            ImageItem(0,getDrawable(requireContext(),R.drawable.ic_periscope_travel1)!!),
            ImageItem(0,getDrawable(requireContext(),R.drawable.ic_periscope_travel2)!!),
            ImageItem(0,getDrawable(requireContext(),R.drawable.ic_periscope_travel3)!!),
            ImageItem(0,getDrawable(requireContext(),R.drawable.ic_periscope_travel4)!!)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getColorList(newPos: Int): List<DotColor> {
        val list = ArrayList<DotColor>()
        for (i in 0..<getImageList().size){

            if(newPos == i){
                list.add(DotColor(i,-1))
            }
            else list.add(DotColor(i,i))

        }
        return list
    }

    private fun onScrolledView(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val position =
                (recyclerView.layoutManager as LinearLayoutManager?)!!
                    .findLastCompletelyVisibleItemPosition()

            dotAdapter.updateList(getColorList(position).toMutableList())
        }

    }

    private lateinit var dotAdapter : DotItemAdapter
}