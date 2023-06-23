package com.testingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.testingapp.CustomAdatper.DeveloperCustomAdapter
import com.testingapp.R
import com.testingapp.databinding.ActivityMainBinding
import com.testingapp.model.ListModelItem
import com.testingapp.viewmodel.DeveloperViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    internal var activityMainBinding: ActivityMainBinding?= null
    internal var loadBar : ProgressBar ? = null
     var mainViewModel: DeveloperViewModel? = null
    private var mDeveloper_CustomAdapter: DeveloperCustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        val recyclerView = activityMainBinding?.viewdeveloper
        loadBar = activityMainBinding!!.loadBar
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        ///init the View Model
        mainViewModel = ViewModelProviders.of(this).get(DeveloperViewModel::class.java)

        //init the Custom adataper
        mDeveloper_CustomAdapter = DeveloperCustomAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(mDeveloper_CustomAdapter)

        getAllDev()
    }

    private fun getAllDev() {
        ///get the list of dev from api response
        mainViewModel!!.allDeveloper.observe(this,
            Observer<List<Any>> { mDeveloperModel ->
                ///if any thing chnage the update the UI
                mDeveloper_CustomAdapter?.setDeveloperList(mDeveloperModel as ArrayList<ListModelItem>)
                loadBar?.visibility = View.GONE
            })
    }
}
