package com.ivanloy.nutricards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders //TODO Change name
                .of(this)
                .get(GameViewModel::class.java)

        model.buildGameController(NumPlayers.TWO_PLAYERS)

        model.fillBoard()

        tv_foodCardOption1Text.text = model
                .getBoardCard(1)
                .toString()

        tv_foodCardOption2Text.text = model
                .getBoardCard(2)
                .toString()

    }
}
