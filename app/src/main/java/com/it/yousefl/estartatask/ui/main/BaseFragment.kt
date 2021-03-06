package com.it.yousefl.estartatask.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.google.android.material.snackbar.Snackbar
import com.it.yousefl.estartatask.R
import com.it.yousefl.estartatask.data.remote.booksresponse.Book
import com.it.yousefl.estartatask.databinding.FragmentBaseBinding
import com.it.yousefl.estartatask.ui.adapters.BooksAdapter
import com.it.yousefl.estartatask.ui.main.viewmodel.BooksViewModel
import com.it.yousefl.estartatask.utils.Status
import com.it.yousefl.estartatask.utils.Utils
import com.it.yousefl.estartatask.utils.Utils.Companion.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val TAG = "BaseFragment"
@AndroidEntryPoint
class BaseFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentBaseBinding
    private lateinit var viewModel: BooksViewModel

    lateinit var booksAdapter: BooksAdapter

    lateinit var doubleBounce: Sprite

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        doubleBounce = DoubleBounce()
        Log.d(TAG, "onCreateView: ")
        binding.btnRetry.setOnClickListener(this)
        getBooks()
        return binding.root
    }
    private fun getBooks() {
        lifecycleScope.launch(Dispatchers.IO){
            viewModel.getBooks()
        }

        Log.d(TAG, "getBooks: ")
        viewModel.books.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.LOADING -> {
                        binding.progerssbar.visibility = View.VISIBLE
                        binding.spinKit.visibility = View.VISIBLE
                        binding.progerssbar.indeterminateDrawable = doubleBounce
                        binding.rcBooks.visibility = View.VISIBLE
                        binding.btnRetry.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.progerssbar.visibility = View.GONE
                        binding.spinKit.visibility = View.GONE
                        binding.rcBooks.visibility = View.VISIBLE
                        binding.btnRetry.visibility = View.GONE
                        result.data?.let { books ->
                            setRcBooks(books)
                        }
                    }
                    Status.ERROR -> {
                        binding.progerssbar.visibility = View.GONE
                        binding.spinKit.visibility = View.GONE
                        Utils.getDialog(requireContext(), result.message, "Server error")
                        binding.rcBooks.visibility = View.GONE
                        binding.btnRetry.visibility = View.VISIBLE
                        if (!isInternetAvailable()) {
                            Snackbar.make(
                                binding.rcBooks,
                                "Check your internet Connection ",
                                Snackbar.LENGTH_LONG
                            ) // .setAction("Action", null)
                                .show()
                        } else {
                            getBooks()
                        }
                    }

                }
            }
        }
    }

    private fun setRcBooks(data: List<Book>) {
        val navController = findNavController()
        booksAdapter = BooksAdapter(requireContext(), data,navController)
        binding.rcBooks.setHasFixedSize(true)
        binding.rcBooks.layoutManager = LinearLayoutManager(requireContext())
        binding.rcBooks.adapter = booksAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_retry->{getBooks()}
        }
    }


}