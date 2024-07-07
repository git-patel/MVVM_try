package com.heaventrinity.mvvm_try

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.heaventrinity.mvvm_try.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), PhotoAdapter.CheckBoxChecked {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecyclerView()
//
//        photoAdapter = PhotoAdapter(this, viewModel.photos.value!!)
//        photoAdapter.setCheckBoxCheckedListener(this)
//        binding.recyclerView.adapter = photoAdapter


        viewModel.photos.observe(this, Observer {
            photoAdapter = PhotoAdapter(this, it)
            photoAdapter.setCheckBoxCheckedListener(this)
            binding.recyclerView.adapter = photoAdapter
        })

    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCheckBoxClick(id: String, pos: Int, checked: Boolean) {
        Toast.makeText(MainActivity@ this, id + pos + checked, Toast.LENGTH_LONG).show()
        if (photoAdapter != null) {
            viewModel.photos.value!!.get(pos).isSelected = checked
            photoAdapter.updateDateSet(viewModel.photos.value!!)
            photoAdapter.notifyItemChanged(pos)
        }
    }
}

