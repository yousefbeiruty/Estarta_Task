package com.it.yousefl.mstarttask.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.it.yousefl.mstarttask.BaseActivity
import com.it.yousefl.mstarttask.R
import com.it.yousefl.mstarttask.databinding.ActivityEventsBinding
import com.it.yousefl.mstarttask.ui.adapters.EventAdapter
import com.it.yousefl.mstarttask.ui.main.MainActivity
import com.it.yousefl.mstarttask.ui.main.viewmodel.DateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsActivity : BaseActivity() {

    lateinit var adapter: EventAdapter
    lateinit var viewModel: DateViewModel
    lateinit var binding:ActivityEventsBinding

    lateinit var img_delete: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_events)
        viewModel = ViewModelProvider(this).get(DateViewModel::class.java)

        img_delete=binding.root.findViewById(R.id.img_delete)

        viewModel.getIds().observe(this, Observer { list->
            if(list.size!=0)
                img_delete.visibility= View.VISIBLE
            else
                img_delete.visibility= View.GONE

            img_delete.setOnClickListener {
                viewModel.deleteDataEventsItems(list)
                img_delete.visibility= View.GONE
            }
        })



        subscribeToObservers()
    }

    private fun subscribeToObservers(){
        viewModel.eventItems.observe(this, androidx.lifecycle.Observer {
            adapter= EventAdapter(this,it,viewModel)
            binding.rc.layoutManager= LinearLayoutManager(this)
            binding.rc.hasFixedSize()
            binding.rc.adapter=adapter
        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent =Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}