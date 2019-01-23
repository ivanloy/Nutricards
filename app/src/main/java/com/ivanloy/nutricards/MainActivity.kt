package com.ivanloy.nutricards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ivanloy.nutricards.adapters.CardStackAdapter
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard
import com.ivanloy.nutricards.util.TextUtil
import com.ivanloy.nutricards.viewmodels.GameViewModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), CardStackListener {

    private val model //TODO Outside?
            by lazy {
                ViewModelProviders
                        .of(this)
                        .get(GameViewModel::class.java)
            }

    private val cardStackView by lazy { findViewById<CardStackView>(R.id.cv_foodCardOption1) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { CardStackAdapter(createList()) }
    private val cardStackView2 by lazy { findViewById<CardStackView>(R.id.cv_foodCardOption2) }
    private val manager2 by lazy { CardStackLayoutManager(this, this) }
    private val adapter2 by lazy { CardStackAdapter(createList()) }

    var deckSize : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCardStackView()

        model.buildGameController(NumPlayers.TWO_PLAYERS) //TODO Reinicia al girar y recrear la activity

        model.fillBoard()
        setCardViewTexts()
        setCardsLeft()
        setPlayerScore()
        setPlayerCardAmounts() //TODO Todos estos metodos en uno
        //TODO ESTO ES TO CUTRE WEY

    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        //Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 1) { //TODO Both cards
            manager.setCanScrollHorizontal(false) //TODO Block
            manager.setCanScrollVertical(false)
        }
        if (manager2.topPosition == adapter2.itemCount - 1) { //TODO Both cards
            manager2.setCanScrollHorizontal(false) //TODO Block
            manager2.setCanScrollVertical(false)
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.card_type)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.card_type)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.VERTICAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)

        manager2.setStackFrom(StackFrom.None)
        manager2.setVisibleCount(3)
        manager2.setTranslationInterval(8.0f)
        manager2.setScaleInterval(0.95f)
        manager2.setSwipeThreshold(0.3f)
        manager2.setMaxDegree(20.0f)
        manager2.setDirections(Direction.VERTICAL)
        manager2.setCanScrollHorizontal(true)
        manager2.setCanScrollVertical(true)

        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        cardStackView2.layoutManager = manager2
        cardStackView2.adapter = adapter2
        cardStackView2.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
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

    fun setPlayerCardAmounts(){
        tv_dairyAmount.text = model
                .getCardAmountOfType(FoodCardTypes.DAIRY)
                .toString()
        tv_fishAmount.text = model
                .getCardAmountOfType(FoodCardTypes.FISH)
                .toString()
        tv_fruitAmount.text = model
                .getCardAmountOfType(FoodCardTypes.FRUIT)
                .toString()
        tv_meatAmount.text = model
                .getCardAmountOfType(FoodCardTypes.MEAT)
                .toString()
        tv_sweetAmount.text = model
                .getCardAmountOfType(FoodCardTypes.SWEET)
                .toString()
        tv_pastaAmount.text = (
                    model.getCardAmountOfType(FoodCardTypes.PASTA) +
                    model.getCardAmountOfType(FoodCardTypes.CEREAL)
                ).toString()

    }

    fun setCardViewTexts(){


    }

    private fun createList(): List<FoodCard> {
        val deck = ArrayList<FoodCard>()
        deck.add("Choripan")
        deck.add("Salchipapa")
        spots.add("Galletanutella")
        spots.add("Comida no sana")
        spots.add("La cebolla me pone cachondo")
        spots.add("Miau?")
        return deck
    }

}
