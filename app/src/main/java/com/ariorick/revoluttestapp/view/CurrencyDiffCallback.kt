package com.ariorick.revoluttestapp.view

import androidx.recyclerview.widget.DiffUtil
import com.ariorick.revoluttestapp.model.Currency

class CurrencyDiffCallback(
    private val oldList: List<Currency>,
    private val newList: List<Currency>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: Currency = oldList[oldItemPosition]
        val newItem: Currency = newList[newItemPosition]
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: Currency = oldList[oldItemPosition]
        val newItem: Currency = newList[newItemPosition]
        return oldItem.value == newItem.value && oldItem.base == newItem.base
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return Any()
    }
}
