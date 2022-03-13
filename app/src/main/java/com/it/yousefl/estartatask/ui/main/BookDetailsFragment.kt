package com.it.yousefl.estartatask.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.it.yousefl.estartatask.R
import com.it.yousefl.estartatask.databinding.FragmentBookDetailsBinding
import com.it.yousefl.estartatask.utils.Constants.DATE
import com.it.yousefl.estartatask.utils.Constants.NAME
import com.it.yousefl.estartatask.utils.Constants.PRICE

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class BookDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var date: String? = null
    private var price: String? = null

    lateinit var binding:FragmentBookDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            date = it.getString(DATE)
            price=it.getString(PRICE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_book_details, container, false)

        binding.tvBookName.text="Book Name: $name"
        binding.tvBookDate.text="Date: $date"
        binding.tvBookPrice.text="Price: $price"
        // Inflate the layout for this fragment
        return binding.root
    }


}