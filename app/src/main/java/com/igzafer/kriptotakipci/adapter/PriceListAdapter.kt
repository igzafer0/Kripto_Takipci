package com.igzafer.kriptotakipci.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.kriptotakipci.R
import com.igzafer.kriptotakipci.model.CryptoModel
import kotlinx.android.synthetic.main.price_row.view.*

class PriceListAdapter : RecyclerView.Adapter<PriceListAdapter.MyViewHolder>() {
    var priceList = ArrayList<CryptoModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PriceListAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.price_row, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: PriceListAdapter.MyViewHolder, position: Int) {
        holder.bind(priceList[position])
    }

    override fun getItemCount(): Int {
        return priceList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data:CryptoModel) {
            itemView.cryptoPrice.text=data.price
            itemView.cryptoName.text=data.currency
        }
    }
}