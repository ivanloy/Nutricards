package com.ivanloy.nutricards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model //TODO Outside?
            by lazy {
                ViewModelProviders
                        .of(this)
                        .get(GameViewModel::class.java)
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.buildGameController(NumPlayers.TWO_PLAYERS) //TODO Reinicia al girar y recrear la activity

        model.fillBoard()
        setCardViewTexts()
        //TODO ESTO ES TO CUTRE WEY

        tv_foodCardOption1Text.setOnClickListener{
            model.drawCardFromBoardToCurrentPlayerHand(0) //TODO Give the last card to other player, next player
            model.fillBoard()
            setCardViewTexts()
        }

        tv_foodCardOption2Text.setOnClickListener{
            model.drawCardFromBoardToCurrentPlayerHand(1)
            model.fillBoard()
            setCardViewTexts()
        }

    }

    fun setCardViewTexts(){

        tv_foodCardOption1Text.text = model //TODO Live data?
                .getBoardCard(0)
                .type
                .toString()

        tv_foodCardOption2Text.text = model
                .getBoardCard(1)
                .type
                .toString()

    }



}
