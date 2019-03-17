package com.ivanloy.nutricards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ivanloy.nutricards.adapters.CardStackAdapter
import com.ivanloy.nutricards.ds.FoodCardsDeck
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard
import com.ivanloy.nutricards.util.TextUtil
import com.ivanloy.nutricards.viewmodels.GameViewModel
import com.makeramen.roundedimageview.RoundedImageView
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import androidx.core.os.HandlerCompat.postDelayed




class MainActivity : AppCompatActivity(), CardStackListener {

    private val model //TODO Outside?
            by lazy {
                ViewModelProviders
                        .of(this)
                        .get(GameViewModel::class.java)
            }

    private val cardStackView by lazy { findViewById<CardStackView>(R.id.cv_foodCardOption1) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { CardStackAdapter(this) }
    private val cardStackView2 by lazy { findViewById<CardStackView>(R.id.cv_foodCardOption2) }
    private val manager2 by lazy { CardStackLayoutManager(this, this) }
    private val adapter2 by lazy { CardStackAdapter(this) }
    private var stackView : CardStackView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCardStackView()

        model.buildGameController(NumPlayers.TWO_PLAYERS) //TODO Reinicia al girar y recrear la activity

        model.fillBoard()
        setCardViewTexts()
        setCardsLeft()
        setPlayerScore()
        setDecks()
        setPlayerCardAmounts() //TODO Todos estos metodos en uno
        //TODO ESTO ES TO CUTRE WEY

    }

    override fun onCardDragging(direction: Direction, ratio: Float, layoutManager: CardStackLayoutManager) {
        getOppositeManager(layoutManager)!!.setCanScrollHorizontal(false) //TODO Call once
        getOppositeManager(layoutManager)!!.setCanScrollVertical(false)
    }

    override fun onCardSwiped(direction: Direction) {


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
        manager.setCanScrollHorizontal(true) //TODO Only affected, method
        manager.setCanScrollVertical(true)
        manager2.setCanScrollHorizontal(true)
        manager2.setCanScrollVertical(true)
    }

    override fun onCardAppeared(view: View, position: Int, layoutManager: CardStackLayoutManager) {
            stackView = null
    }

    override fun onCardReleased() { //TODO Fix only call once
    }

    override fun onCardDisappeared(view: View, position: Int) {
        manager.setCanScrollHorizontal(true) //TODO Only affected, method
        manager.setCanScrollVertical(true)
        manager2.setCanScrollHorizontal(true)
        manager2.setCanScrollVertical(true)
    }

    override fun onCardManuallySwiped(
            view: View?,
            position: Int,
            direction: Direction?,
            layoutManager : CardStackLayoutManager) {

        Log.d("CardStackView", "onCard" +
                view!!.findViewById<RoundedImageView>(R.id.card_image).contentDescription)

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) //TODO Method

        stackView = getLastCardStackView(layoutManager)!!

        val typeString = view!!
                .findViewById<RoundedImageView>(R.id.card_image)
                .contentDescription
                .toString()

        val type : FoodCardTypes = getFoodCardTypeWithString(typeString)

        if(direction == Direction.Bottom){
            model.addCardToCurrentPlayerHand(type)
            swipeCardUp(layoutManager)
            setPlayerScore()
            setPlayerCardAmounts()
        }else{
            model.addCardToNextPlayerHand(type)
            swipeCardDown(layoutManager)
        }

        model.nextPlayer()
        if(model.getCurrentPlayer() != 0) randomAISwipe()
        model.nextPlayer()
    }

    private fun randomAISwipe() {

        if(model.getCurrentDeckSize() < 2) return

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) //TODO Method


        Handler().postDelayed({

            val typeString = manager//CORREGIR, viewmodel
                    .topView
                    .findViewById<RoundedImageView>(R.id.card_image)
                    .contentDescription
                    .toString()
            val typeString2 = manager2//CORREGIR, viewmodel
                    .topView
                    .findViewById<RoundedImageView>(R.id.card_image)
                    .contentDescription
                    .toString()
            val type : FoodCardTypes = getFoodCardTypeWithString(typeString)
            val type2 : FoodCardTypes = getFoodCardTypeWithString(typeString2)


            var random = Random()
            var randInt = random.nextInt(2)

            when(randInt){
                0 -> {
                    model.addCardToNextPlayerHand(type)
                    model.addCardToCurrentPlayerHand(type2)
                    manager.setSwipeAnimationSetting(getTopSwipeAnimationSetting())
                    manager2.setSwipeAnimationSetting(getBottomSwipeAnimationSetting())
                }
                1 -> {
                    model.addCardToNextPlayerHand(type2)
                    model.addCardToCurrentPlayerHand(type)
                    manager.setSwipeAnimationSetting(getBottomSwipeAnimationSetting())
                    manager2.setSwipeAnimationSetting(getTopSwipeAnimationSetting())
                }
            }
            cardStackView!!.swipe()
            cardStackView2!!.swipe()
            setPlayerCardAmounts()
            setPlayerScore()
            Handler().postDelayed({
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }, 300)

        }
                , 600)

    }

    private fun getLastCardStackView(layoutManager : CardStackLayoutManager) : CardStackView? {
        var ret : CardStackView? = null
        when(layoutManager){
            manager -> ret = cardStackView2
            manager2 -> ret = cardStackView
        }
        return ret
    }

    private fun getStackViewWithManager(layoutManager : CardStackLayoutManager) : CardStackView? {
        var ret : CardStackView? = null
        when(layoutManager){
            manager -> ret = cardStackView
            manager2 -> ret = cardStackView2
        }
        return ret
    }

    private fun getOppositeManager(layoutManager : CardStackLayoutManager) : CardStackLayoutManager? { //TODO Pruebas
        var ret : CardStackLayoutManager? = null
        when(layoutManager){
            manager -> ret = manager2
            manager2 -> ret = manager
        }
        return ret
    }

    private fun swipeCardUp(layoutManager : CardStackLayoutManager){
        val setting = getTopSwipeAnimationSetting()
        manager.setSwipeAnimationSetting(setting)
        manager2.setSwipeAnimationSetting(setting)

        val typeString = getOppositeManager(layoutManager)!!//CORREGIR, viewmodel
                .topView
                .findViewById<RoundedImageView>(R.id.card_image)
                .contentDescription
                .toString()

        val type : FoodCardTypes = getFoodCardTypeWithString(typeString)
        model.addCardToNextPlayerHand(type)

        getLastCardStackView(layoutManager)!!.swipe()
    }

    private fun getTopSwipeAnimationSetting(): SwipeAnimationSetting {
        val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Top)
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .build()
        return setting
    }

    private fun swipeCardDown(layoutManager : CardStackLayoutManager){
        val setting = getBottomSwipeAnimationSetting()
        manager.setSwipeAnimationSetting(setting)
        manager2.setSwipeAnimationSetting(setting) //TODO only 1

        val stackView : CardStackView = getLastCardStackView(layoutManager)!!

        val typeString = getOppositeManager(layoutManager)!!//CORREGIR, viewmodel
                .topView
                .findViewById<RoundedImageView>(R.id.card_image)
                .contentDescription
                .toString()

        val type : FoodCardTypes = getFoodCardTypeWithString(typeString)
        model.addCardToCurrentPlayerHand(type)
        setPlayerScore()
        setPlayerCardAmounts() //TODO MEthod
        stackView.swipe()
    }

    private fun getBottomSwipeAnimationSetting(): SwipeAnimationSetting {
        val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .build()
        return setting
    }

    private fun getFoodCardTypeWithString(string : String) : FoodCardTypes{
        var ret : FoodCardTypes = FoodCardTypes.BLANK
        when(string){ //TODO Do in enum class
            "BLANK" -> ret = FoodCardTypes.BLANK
            "SWEET" -> ret = FoodCardTypes.SWEET
            "MEAT" -> ret = FoodCardTypes.MEAT
            "FISH" -> ret = FoodCardTypes.FISH
            "FORK" -> ret = FoodCardTypes.FORK
            "DAIRY" -> ret = FoodCardTypes.DAIRY
            "FRUIT" -> ret = FoodCardTypes.FRUIT
            "VEGETABLE" -> ret = FoodCardTypes.VEGETABLE
            "PASTA" -> ret = FoodCardTypes.PASTA
            "CEREAL" -> ret = FoodCardTypes.CEREAL
        }
        return ret
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun setDecks(){
        var decks : MutableList<FoodCardsDeck> = model.getBoardDecks()
        adapter.setData(decks[0].toList())
        adapter2.setData(decks[1].toList())
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
        deck.add(FoodCard(FoodCardTypes.DAIRY))
        deck.add(FoodCard(FoodCardTypes.SWEET))
        deck.add(FoodCard(FoodCardTypes.FISH))
        deck.add(FoodCard(FoodCardTypes.CEREAL))
        deck.add(FoodCard(FoodCardTypes.MEAT))
        deck.add(FoodCard(FoodCardTypes.FORK))
        deck.add(FoodCard(FoodCardTypes.FRUIT))
        deck.add(FoodCard(FoodCardTypes.BLANK))
        return deck
    }

}
