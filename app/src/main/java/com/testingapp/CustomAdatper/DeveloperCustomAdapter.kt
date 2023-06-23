package com.testingapp.CustomAdatper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.testingapp.R
import com.testingapp.databinding.RowListItemBinding
import com.testingapp.model.DeveloperModel
import com.testingapp.model.ListModelItem
import java.util.*


class DeveloperCustomAdapter :
    RecyclerView.Adapter<DeveloperCustomAdapter.DeveloperViewHolder>() {

    private var mDeveloperModel: ArrayList<ListModelItem>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DeveloperViewHolder {
        val mDeveloperListItemBinding = DataBindingUtil.inflate<RowListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.row_list_item, viewGroup, false
        )

        return DeveloperViewHolder(mDeveloperListItemBinding)
    }

    override fun onBindViewHolder(mDeveloperViewHolder: DeveloperViewHolder, i: Int) {
        val currentStudent = mDeveloperModel!![i]

        
        mDeveloperViewHolder.mDeveloperListItemBinding.developerModel = currentStudent


    }

    override fun getItemCount(): Int {
        return if (mDeveloperModel != null) {
            mDeveloperModel!!.size
        } else {
            0
        }
    }

    fun setDeveloperList(mDeveloperModel: ArrayList<ListModelItem>) {
        this.mDeveloperModel = mDeveloperModel
        notifyDataSetChanged()
    }

    inner class DeveloperViewHolder(var mDeveloperListItemBinding: RowListItemBinding) :
        RecyclerView.ViewHolder(mDeveloperListItemBinding.root)
}
