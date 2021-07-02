package com.it.yousefl.mstarttask.ui.main

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.it.yousefl.mstarttask.BaseActivity
import com.it.yousefl.mstarttask.R
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.databinding.ActivityMainBinding
import com.it.yousefl.mstarttask.ui.EventsActivity
import com.it.yousefl.mstarttask.ui.adapters.EventAdapter
import com.it.yousefl.mstarttask.ui.main.viewmodel.DateViewModel
import com.it.yousefl.mstarttask.utils.Resource
import com.it.yousefl.mstarttask.utils.Status
import com.it.yousefl.mstarttask.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener {

    private var flag: Boolean? = null
    private lateinit var myCalendar: Calendar
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DateViewModel
    private var server_time: String = ""
    private var id: Int = 0
    private var flag_update = false
    private var date_in_geo: String = ""
    private var date_in_hijri: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(DateViewModel::class.java)

        myCalendar = Calendar.getInstance()
        binding.tvSelectDate.setOnClickListener(this)
        binding.btnConvert.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
        binding.btnShow.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)

        flag = !binding.tvDateHijry.text.toString().isEmpty()


        flag_update = intent?.getBooleanExtra("flag_update", false) == true
        Log.d(TAG, "flag_update= $flag_update")
        if (flag_update) {
            id = intent.getIntExtra("id", 0)
            binding.btnSave.visibility = View.GONE
            binding.btnUpdate.visibility = View.VISIBLE
            binding.tvSelectDate.text = intent.getStringExtra("gregorian_date")
            binding.tvDateHijry.text = intent.getStringExtra("hijri")
            binding.etEventName.setText(intent.getStringExtra("event_name"))
            binding.etEventDescription.setText(intent.getStringExtra("event_description"))

            date_in_geo = intent.getStringExtra("gregorian_date").toString()
            date_in_hijri = intent.getStringExtra("hijri").toString()

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("date", binding.tvSelectDate.text.toString())
        outState.putString("date_hijri", binding.tvDateHijry.text.toString())
        outState.putString("event_name", binding.etEventName.text.toString())
        outState.putString("event_description", binding.etEventDescription.text.toString())
        flag?.let { outState.putBoolean("flag", it) }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvSelectDate.text = savedInstanceState.getString("date")
        binding.tvDateHijry.text = savedInstanceState.getString("date_hijri")
        binding.etEventName.setText(savedInstanceState.getString("event_name"))
        binding.etEventDescription.setText(savedInstanceState.getString("event_description"))

        date_in_geo = binding.tvSelectDate.text.toString()
        date_in_hijri = binding.tvDateHijry.text.toString()
        if (savedInstanceState.getBoolean("flag")) {
            binding.lnHijry.visibility = View.VISIBLE
        }
    }

    var date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_select_date -> DatePickerDialog(
                this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
            R.id.btn_convert -> {
                if (date_in_geo.isNotEmpty())
                    convertDate()
                else
                    Utils.getDialog(this, "please enter select date fields", "Empty date...!")

            }
            R.id.btn_save -> {
                if (date_in_geo.isNotEmpty() &&
                    date_in_hijri.isNotEmpty() &&
                    date_in_geo.isNotEmpty() &&
                    date_in_hijri.isNotEmpty()
                ) {
                    addEvent()
                } else {
                    Utils.getDialog(this, "please enter empty fields", "Empty fields...!")
                }
            }
            R.id.btn_show -> {
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_update -> {
                if (date_in_geo.isNotEmpty() &&
                    date_in_hijri.isNotEmpty() &&
                    binding.etEventName.text.toString().isNotEmpty() &&
                    binding.etEventDescription.text.toString().isNotEmpty()
                ) {
                    update()
                } else {
                    Utils.getDialog(this, "please enter empty fields", "Empty fields...!")
                }
            }
            //else-{}
        }
    }

    private fun update() {
        viewModel.update(
            EventItem(
                id = id, event_name = binding.etEventName.text.toString(),
                event_description = binding.etEventDescription.text.toString(),
                gregorian_date = binding.tvSelectDate.text.toString(),
                hijri = binding.tvDateHijry.text.toString(), serve_date_time = ""
            )
        )
        Utils.onSNACK(binding.etEventName, "Event has been Updated successfully")
    }

    private fun addEvent() {
        viewModel.insertEventItem(
            event_name = binding.etEventName.text.toString(),
            event_description = binding.etEventDescription.text.toString(),
            gregorian_date = binding.tvSelectDate.text.toString(),
            hijri = binding.tvDateHijry.text.toString(), serve_date_time = server_time
        )
        Utils.onSNACK(binding.etEventName, "Event has been added successfully")
    }

    private fun convertDate() {
        date_in_hijri = binding.tvSelectDate.text.toString()
        viewModel.convert(binding.tvSelectDate.text.toString())
        ObserveDate()
    }

    private fun ObserveDate() {
        viewModel.date.observe(this) {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        hideDialog()
                        val data = result.data
                        //Timer("data= $data ")
                        Log.d(TAG, "ObserveDate: Data= $data")
                        binding.tvDateHijry.text = data?.data?.hijri?.date
                        server_time = ""
                        binding.lnHijry.visibility = View.VISIBLE
                        flag = true
                    }
                    Status.ERROR -> {
                        hideDialog()
                        Log.d(TAG, "ObserveDate: ERROR")
//                        val show = Snackbar.make(
//                            binding.tvSelectDate,
//                            result.message ?: "An unknown error occured.",
//                            Snackbar.LENGTH_LONG
//                        ).show()
                        if (!Utils.isInternetAvailable()) {
                            Snackbar.make(
                                binding.root,
                                "Check your internet Connection",
                                Snackbar.LENGTH_LONG
                            )
                                // .setAction("Action", null)
                                .show()

                            //Resource.error("Check your internet Connection", null)
                        } else {
                            Utils.getDialog(this, result.message, "Server error")
                            //   convertDate();
                        }

                    }
                    Status.LOADING -> {
                        showDialog()
                        Log.d(TAG, "ObserveDate: LOADING")
                    }
                }

            }


        }
    }

    private fun updateLabel() {
        val myFormat = "MM-dd-yyy" //In which you need put here

        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date_in_geo = binding.tvSelectDate.text.toString()
        binding.tvSelectDate.text = sdf.format(myCalendar.time)
    }


}