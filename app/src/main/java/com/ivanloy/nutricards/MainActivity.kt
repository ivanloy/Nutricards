package com.ivanloy.nutricards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.util.TextUtil
import com.ivanloy.nutricards.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model //TODO Outside?
            by lazy {
                ViewModelProviders
                        .of(this)
                        .get(GameViewModel::class.java)
            }

    var deckSize : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.buildGameController(NumPlayers.TWO_PLAYERS) //TODO Reinicia al girar y recrear la activity

        model.fillBoard()
        setCardViewTexts()
        setCardsLeft()
        setPlayerScore()
        //TODO ESTO ES TO CUTRE WEY

        cv_foodCardOption1.setOnClickListener{
            model.drawCardFromBoardToCurrentPlayerHand(0) //TODO Give the last card to other player, next player
            model.fillBoard()
            setCardViewTexts()
            setCardsLeft()
            setPlayerScore()
        }

        cv_foodCardOption2.setOnClickListener{
            model.drawCardFromBoardToCurrentPlayerHand(1)
            model.fillBoard()
            setCardViewTexts()
            setCardsLeft()
            setPlayerScore()
        }

    }

    fun setCardsLeft(){
        //TODO Live dataaaaa
        val currentDeckSize = model.getCurrentDeckSize()
        val initialDeckSize = model.getInitialDeckSize()

        tv_cardsLeftInDeck.text = TextUtil
                .fromHtml("<big>$currentDeckSize</big>/<small>$initialDeckSize</small>");
    }

    fun setPlayerScore(){
        tv_playerScore.text = model
                .calculateCurrentPlayerScore().toString()
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
