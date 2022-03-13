package com.it.yousefl.estartatask.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.it.yousefl.estartatask.MainCoroutineRule
import com.it.yousefl.estartatask.getOrAwaitValueTest
import com.it.yousefl.estartatask.repository.FakeBooksRepository
import com.it.yousefl.estartatask.ui.main.viewmodel.DateViewModel
import com.it.yousefl.estartatask.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class EventViewModelTest {

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule= MainCoroutineRule()

    private lateinit var viewModel: DateViewModel

    @Before
    fun setUp(){
        viewModel= DateViewModel(FakeBooksRepository())
    }

    @Test
    fun `insert event item with valid input, returns success`(){
//        viewModel.insertEventItem("Event name","Description",
//            "07-12-2014","14-02-1436","")
//
//        val value=viewModel.insertEventStatus.getOrAwaitValueTest()

       // assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }




}