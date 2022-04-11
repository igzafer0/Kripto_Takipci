package com.igzafer.kriptotakipci.view

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.igzafer.kriptotakipci.R
import com.igzafer.kriptotakipci.adapter.PriceListAdapter
import com.igzafer.kriptotakipci.model.CryptoModel
import com.igzafer.kriptotakipci.util.ApiStatus
import com.igzafer.kriptotakipci.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var priceListAdapter: PriceListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRecycler()
        getLiveData()
    }

    private fun initRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            priceListAdapter = PriceListAdapter()
            adapter = priceListAdapter
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getLiveData() {
        viewModel.price_status.observe(this){data->
            data.let {
                if (data==ApiStatus.LOADING){
                    loadingWidget.visibility=View.VISIBLE
                }else{
                    loadingWidget.visibility=View.GONE
                }
            }
        }
        viewModel.price_data.observe(this) { data ->
            data.let {
                if (it != null) {

                    priceListAdapter.priceList = it as ArrayList<CryptoModel>
                    priceListAdapter.notifyDataSetChanged()
                } else {


                }
            }
        }
        viewModel.refreshData()

    }

}