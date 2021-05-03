package com.cc.carhireservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cc.carhireservice.view.CarEntryFragment
import com.cc.carhireservice.view.CarListFragment
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), CarEntryFragment.UpdateCarList  {

    private var listFrag:CarListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        com.cc.carhireservice.util.Logger.log("onCreate")

        listFrag = supportFragmentManager.findFragmentById(R.id.carListFragment) as CarListFragment
    }

    override fun update() {
        com.cc.carhireservice.util.Logger.log("activity update")
        listFrag?.update()
    }
}