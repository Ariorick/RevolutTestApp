package com.ariorick.revoluttestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ariorick.revoluttestapp.R
import com.ariorick.revoluttestapp.mvp.MvpPresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_currency.*
import javax.inject.Inject

class CurrencyFragment : DaggerFragment(), View {


    @Inject
    lateinit var presenter: Presenter
    @Inject
    lateinit var adapter: CurrencyAdapter

    private lateinit var snackbar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pb_loading.show()
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                if (adapter.itemCount == 0) pb_loading.show()
                else pb_loading.hide()
            }
        })
        rv_currency.adapter = adapter
        rv_currency.layoutManager = LinearLayoutManager(context)

        snackbar = Snackbar.make(view, "Offline", Snackbar.LENGTH_INDEFINITE)
        presenter.attachView(this)
    }

    override fun showConnectionError() {
        if (!snackbar.isShownOrQueued) snackbar.show()
    }

    override fun hideConnectionError() {
        snackbar.dismiss()
    }

    override fun scrollUp() {
        rv_currency?.scrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

}

interface Presenter : MvpPresenter<View>
