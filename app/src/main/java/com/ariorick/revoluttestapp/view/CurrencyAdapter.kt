package com.ariorick.revoluttestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ariorick.revoluttestapp.R
import com.ariorick.revoluttestapp.dagger.CurrencyFragmentScope
import com.ariorick.revoluttestapp.model.Currency

@CurrencyFragmentScope
class CurrencyAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var data: List<Currency> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}