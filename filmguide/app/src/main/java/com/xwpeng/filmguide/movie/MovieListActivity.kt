package com.xwpeng.filmguide.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xwpeng.filmguide.R
import kotlinx.android.synthetic.main.activity_item_list.*

class MovieListActivity :AppCompatActivity(){

    private var mTwoPane:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(toolbar)
        toolbar.title = title

    }

}