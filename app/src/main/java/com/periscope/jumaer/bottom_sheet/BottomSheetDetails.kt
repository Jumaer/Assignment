package com.periscope.jumaer.bottom_sheet

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.periscope.jumaer.R
import com.periscope.jumaer.adapter.DetailsItemAdapter
import com.periscope.jumaer.adapter.SubContainerBottomSheetAdapter
import com.periscope.jumaer.databinding.BottomSheetLayoutMainBinding
import com.periscope.jumaer.model.DetailItem
import com.periscope.jumaer.model.SubSection
import com.periscope.jumaer.ui_support.setItemTouchHelper

class BottomSheetDetails (
    context: Context,
    private val listOfAll : ArrayList<SubSection>,
    private val onItemClick : (position : Int) ->Unit
) {
    private var binding: BottomSheetLayoutMainBinding
    private var bottomSheet: BottomSheetDialog
    private var mainAdapter: SubContainerBottomSheetAdapter
    init {
        binding =
            BottomSheetLayoutMainBinding.inflate(
                LayoutInflater.from(context),
                null,
                false
            ).apply {

                mainAdapter =  SubContainerBottomSheetAdapter(context, listOfAll,
                    onItemClick
                )

                rvMain.apply {
                    adapter = mainAdapter
                    setHasFixedSize(true)

                }

                imageView.setOnClickListener {
                    dismiss()
                }


            }
        bottomSheet = BottomSheetDialog(context, R.style.BottomSheetDialog).apply {
            setContentView(binding.root)
        }
    }


    fun show() {
        if (!bottomSheet.isShowing) bottomSheet.show()
    }

    fun dismiss() {
        if (bottomSheet.isShowing) bottomSheet.dismiss()
    }




}