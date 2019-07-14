package com.ariorick.revoluttestapp.view

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ariorick.revoluttestapp.R
import com.ariorick.revoluttestapp.dagger.CurrencyFragmentScope
import com.ariorick.revoluttestapp.model.Currency
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@CurrencyFragmentScope
class CurrencyAdapter @Inject constructor() : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    lateinit var presenter: Presenter
    private var data: List<Currency> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position].name, data[position].isBase, data[position].value)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        onBindViewHolder(holder, position)
    }

    fun setData(data: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(this.data, data))
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val etValue: EditText = itemView.findViewById(R.id.et_value)
        private var textWatcher: TextWatcher = TextWatcher()

        fun bind(name: String, isBase: Boolean, value: Float) {
            tvName.text = name

            etValue.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) presenter.setBase(name, value) }

            etValue.removeTextChangedListener(textWatcher)
            if (!etValue.isFocused) etValue.setText(String.format("$value", Locale.ENGLISH))
            if (isBase) etValue.addTextChangedListener(textWatcher)
        }

        inner class TextWatcher : android.text.TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                try {
                    presenter.setBase(
                        data[this@ViewHolder.adapterPosition].name,
                        Scanner(p0.toString())
                            .useLocale(Locale.ENGLISH)
                            .nextFloat()
                    )
                } catch (e: Exception) {
                    presenter.setBase(data[this@ViewHolder.adapterPosition].name, 0f)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }

    }
}