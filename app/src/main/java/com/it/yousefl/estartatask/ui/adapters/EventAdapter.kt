package com.it.yousefl.mstarttask.ui.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.it.yousefl.mstarttask.R
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.databinding.ItemEventBinding
import com.it.yousefl.mstarttask.ui.main.MainActivity
import com.it.yousefl.mstarttask.ui.main.viewmodel.DateViewModel

private const val TAG = "EventAdapter"
class EventAdapter(private val context:Context,private val list:List<EventItem>,private val viewModel:
DateViewModel) :
    RecyclerView.Adapter<EventAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      val binding: ItemEventBinding=DataBindingUtil.inflate(LayoutInflater.from(context),
      R.layout.item_event,parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.item=list[position]
//        holder.binding.imgDelete.setOnClickListener(View.OnClickListener {
//            viewModel.deleteEventItem(list[position])
//        })

        holder.binding.chDelete.setOnCheckedChangeListener { buttonView, isChecked -> //set your object's last status
            if (isChecked) {

                list[position].id?.let { viewModel.addEventId(it) }
            } else {
                list[position].id?.let { viewModel.removeEventId(it) }
            }
         //   Log.d(TAG, "onBindViewHolder: arrayList=${viewModel.getid}")
        }


        holder.binding.imgEdit.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("event_name",list[position].event_name)
            intent.putExtra("event_description",list[position].event_description)
            intent.putExtra("gregorian_date",list[position].gregorian_date)
            intent.putExtra("hijri",list[position].hijri)
            intent.putExtra("flag_update",true)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

   data class Holder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)


}