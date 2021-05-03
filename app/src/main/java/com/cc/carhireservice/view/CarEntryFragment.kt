package com.cc.carhireservice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cc.carhireservice.MainActivity
import com.cc.carhireservice.databinding.CarEntryFragmentBinding
import com.cc.carhireservice.model.data.Car
import com.cc.carhireservice.model.db.CarHireServiceDatabase
import kotlinx.coroutines.*
import java.util.logging.Logger


class CarEntryFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding:CarEntryFragmentBinding
    private var delegate:UpdateCarList? = null

    interface UpdateCarList {
        fun update ()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = CarEntryFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = context as MainActivity
        delegate = activity


        binding.addCarButton.setOnClickListener {
            addData()
        }


    }

    private fun addData () {
        if (binding.carNameTextView.text.isEmpty()) {
            return
        }

        if (binding.pricePerDayTextView.text.isEmpty()) {
            return
        }

        if (binding.licenseTextView.text.isEmpty()) {
            return
        }

        val price:Int? = binding.pricePerDayTextView.text.toString().toIntOrNull()

        if (price != null) {
            val car = Car(0, binding.carNameTextView.text.toString(), price, binding.licenseTextView.text.toString())
            val db = context?.let { CarHireServiceDatabase.getInstance(it)}

            launch {
                Dispatchers.IO {
                    com.cc.carhireservice.util.Logger.log("thread : ${Thread.currentThread()}")
                    db?.carDao()?.insertAll(car)
                    delegate?.update()
                }

            }


        }






    }


}