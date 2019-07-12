package com.ariorick.revoluttestapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ariorick.revoluttestapp.R
import com.ariorick.revoluttestapp.mvp.MvpPresenter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CurrencyFragment : DaggerFragment() {

    @Inject
    lateinit var presenter: Presenter

    companion object {
        fun newInstance(): CurrencyFragment {
            val args = Bundle()
            val fragment = CurrencyFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

interface Presenter : MvpPresenter<View> {

}
