package com.cc.carhireservice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cc.carhireservice.databinding.CarEntryFragmentBinding
import com.cc.carhireservice.databinding.CarListFragmentBinding
import com.cc.carhireservice.util.Logger

class CarListFragment : Fragment () {

    private var binding:CarListFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = CarListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CarListAdapter()
        binding?.carListRecyclerView?.adapter = adapter
        context?.let { adapter.update(it) }
    }

    fun update () {
        Logger.log("car list fragment update")
        val adapter = binding?.carListRecyclerView?.adapter as CarListAdapter
        context?.let { adapter.update(it) }
    }

}