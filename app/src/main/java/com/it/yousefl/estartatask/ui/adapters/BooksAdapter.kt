package com.it.yousefl.estartatask.ui.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.it.yousefl.estartatask.R
import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.databinding.ItemBookBinding
import com.it.yousefl.estartatask.utils.Constants.DATE
import com.it.yousefl.estartatask.utils.Constants.NAME
import com.it.yousefl.estartatask.utils.Constants.PRICE

class BooksAdapter (private val context: Context, private val list:List<Book>,
 private val navController:NavController
) :
    RecyclerView.Adapter<BooksAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.Holder {
        val binding: ItemBookBinding= DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_book,parent,false)
        return BooksAdapter.Holder(binding)
    }

    override fun onBindViewHolder(holder: BooksAdapter.Holder, position: Int) {
        holder.binding.item=list[position]

        holder.binding.img.setOnClickListener {
            val bundle=Bundle()
            bundle.putString(DATE,list[position].createdAt)
            bundle.putString(NAME,list[position].name)
            bundle.putString(PRICE,list[position].price)

            navController.navigate(R.id.action_baseFragment_to_bookDetailsFragment2,bundle)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    data class Holder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)
}