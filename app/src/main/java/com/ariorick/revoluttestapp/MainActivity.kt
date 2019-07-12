package com.ariorick.revoluttestapp

import android.os.Bundle
import com.ariorick.revoluttestapp.fragment.CurrencyFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CurrencyFragment.newInstance())
            .commit()

    }
}
