package com.myxplor.toyrobot.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.myxplor.toyrobot.R
import com.myxplor.toyrobot.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var stringAdapter: StringListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        initView()
        observe()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        stringAdapter = StringListAdapter()
        binding.recyclerView.adapter = stringAdapter
    }

    private fun observe() {
        mainViewModel.commandLog.observe(this, Observer {
            stringAdapter.setList(it)
        })
    }
}
