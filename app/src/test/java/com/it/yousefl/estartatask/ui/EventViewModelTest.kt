package com.it.yousefl.mstarttask.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.it.yousefl.mstarttask.MainCoroutineRule
import com.it.yousefl.mstarttask.data.local.EventItem
import com.it.yousefl.mstarttask.getOrAwaitValueTest
import com.it.yousefl.mstarttask.repository.FakeEventRepository
import com.it.yousefl.mstarttask.ui.main.viewmodel.DateViewModel
import com.it.yousefl.mstarttask.utils.Status
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
        viewModel= DateViewModel(FakeEventRepository())
    }

    @Test
    fun `insert event item with valid input, returns success`(){
        viewModel.insertEventItem("Event name","Description",
            "07-12-2014","14-02-1436","")

        val value=viewModel.insertEventStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }




}