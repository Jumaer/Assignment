package com.periscope.jumaer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.periscope.jumaer.R
import com.periscope.jumaer.adapter.InspectionItemAdapter
import com.periscope.jumaer.bottom_sheet.BottomSheetDetails
import com.periscope.jumaer.databinding.FragmentLauncherBinding
import com.periscope.jumaer.model.DetailItem
import com.periscope.jumaer.model.InspectionItem
import com.periscope.jumaer.model.SubSection
import java.text.FieldPosition


class LauncherFragment : Fragment() {
    private lateinit var binding: FragmentLauncherBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLauncherBinding.inflate(inflater, container, false)

        val baseAdapter = InspectionItemAdapter(requireContext(),getList(),::onItemClick)

        binding.rvMain.apply {
            adapter = baseAdapter
            setHasFixedSize(true)
        }


        return binding.root
    }

    private fun getList(): List<InspectionItem> {
        val list = ArrayList<InspectionItem>()

        list.add(InspectionItem(0,"Holiday in express Franklin","Weekly Inspection","18, Dec"))
        list.add(InspectionItem(0,"Holiday in express Franklin","Above Property Assessment","18, Dec"))
        list.add(InspectionItem(0,"Holiday in express Franklin","Adult Shift Checklist","18, Dec"))
        list.add(InspectionItem(0,"Holiday in express Franklin","Do Not Disturb- Daily Tracking List","18, Dec"))
        list.add(InspectionItem(0,"Holiday in express Franklin","Annual Inspection","18, Dec"))
        list.add(InspectionItem(0,"Holiday in express Franklin","Biennial Inspection ","18, Dec"))
        list.add(InspectionItem(0,"Holiday in express Franklin","Weekly Inspection","18, Dec"))

        return list
    }

    private lateinit var bsShow : BottomSheetDetails

    private fun onItemClick(position: Int){

        if(!::bsShow.isInitialized){
            bsShow = BottomSheetDetails(requireContext(),getListOfBs(),::onBsItem)
        }
         bsShow.show()

    }

    private fun onBsItem(position: Int){
        bsShow.dismiss()
        findNavController().navigate(R.id.action_launcherFragment_to_imageSliderFragment)
    }
    private fun getListOfBs(): ArrayList<SubSection> {
        val listOfAll = ArrayList<SubSection> ()

        listOfAll.add(SubSection(getAllData(),"Cleanliness Inspection"))
        listOfAll.add(SubSection(getAllData(),"Bedrooms"))
        listOfAll.add(SubSection(getAllData(),"AM Inspections"))
        listOfAll.add(SubSection(getAllData(),"Leaving Areas"))

        return listOfAll
    }

    private fun getAllData(): ArrayList<DetailItem> {
        val listOfAll = ArrayList<DetailItem>()
        listOfAll.add(DetailItem(0,"Every inch of the room has been sanitized","2", "0", "1", R.color.white))
        listOfAll.add(DetailItem(0,"Every inch of the room has been sanitized","2", "0", "1", R.color.white))
        listOfAll.add(DetailItem(0,"Every inch of the room has been sanitized","2", "0", "1", R.color.white))


        return listOfAll
    }
}