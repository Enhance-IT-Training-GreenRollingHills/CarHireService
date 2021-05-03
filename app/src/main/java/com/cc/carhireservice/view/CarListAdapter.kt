package com.cc.carhireservice.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cc.carhireservice.MainActivity
import com.cc.carhireservice.databinding.CarListRowBinding
import com.cc.carhireservice.model.data.Car
import com.cc.carhireservice.model.db.CarHireServiceDatabase
import com.cc.carhireservice.util.Logger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CarListAdapter: RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    private var list: List<Car> = emptyList()

    class CarListViewHolder(
        val binding: CarListRowBinding,
        itemView: ConstraintLayout = binding.root



    ) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        //TODO("Not yet implemented")

        val binding = CarListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return list.size
    }

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val car = list[position]

        holder.binding.listRowCarName.text = car.name
        holder.binding.listRowPrice.text = car.pricePerDay.toString()
        holder.binding.listRowLicense.text = car.licenseTag
        holder.binding.listRowSwitch.isChecked = car.available

        holder.binding.listRowSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            car.available = isChecked
            GlobalScope.launch {
                CarHireServiceDatabase.getInstance(holder.itemView.context).carDao().updateCar(car)

            }
        }
    }

    fun update (context: Context) {
        Logger.log("adapter update")
        GlobalScope.launch {
            list = CarHireServiceDatabase.getInstance(context).carDao().getAll()
            for (car in list) {
                Logger.log("car id : ${car.id}, car name : ${car.name}")
            }

        }
        val mainActivity = context as MainActivity
        mainActivity.runOnUiThread {
            notifyDataSetChanged()

        }
    }
}