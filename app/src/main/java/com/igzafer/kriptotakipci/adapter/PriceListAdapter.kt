package com.igzafer.kriptotakipci.adapter

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.igzafer.kriptotakipci.R
import com.igzafer.kriptotakipci.model.CryptoModel
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.price_row.view.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


class PriceListAdapter : RecyclerView.Adapter<PriceListAdapter.MyViewHolder>() {
    var priceList = ArrayList<CryptoModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.price_row, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(priceList[position])
    }

    override fun getItemCount(): Int {
        return priceList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("ResourceAsColor")
        fun bind(data: CryptoModel) {
            if (data.price.toDouble() > itemView.cryptoPrice.text.toString()
                    .toDouble()
            ) itemView.cryptoPrice.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.success
                )
            ) else itemView.cryptoPrice.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.error)
            )


            itemView.cryptoPrice.text = data.price
            itemView.cryptoName.text = data.currency
            itemView.cryptoNameDetailed.text = data.name
            itemView.cryptoImageView.loadUrl(data.logo_url)


        }

        fun ImageView.loadUrl(url: String) {
            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
                .build()
            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)
                .data(url)
                .target(this)
                .dispatcher(Dispatchers.IO)
                .build()

            imageLoader.enqueue(request)
        }
    }
}