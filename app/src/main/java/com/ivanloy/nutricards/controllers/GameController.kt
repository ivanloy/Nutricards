package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.FoodCardsDeck
import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.NumPlayers
import com.ivanloy.nutricards.gameelements.FoodCard

class GameController(val numPlayers: NumPlayers = NumPlayers.DEFAULT) : GameControllerI
{


    var deck : FoodCardsDeck
    var hands : MutableList< Hand< FoodCard >>
    var board : Hand< FoodCard >

    init {

        deck = FoodCardsDeck()
        hands = ArrayList()
        board = Hand()

        buildDeck()
        buildHands()

    }

    var currentPlayer : Int = 0
        private set

    override fun getBoardCard(index: Int) : FoodCard {
        return board.peekCard(index)
    }

    override fun nextPlayer(){
        if(currentPlayer == numPlayers.nPlayers - 1) currentPlayer = 0 else currentPlayer++
    }

    override fun drawCardToCurrentPlayerHand(){
        hands[currentPlayer]
                .addCard(deck.drawCard())
    }

    override fun fillBoard(){
        board = Hand()
        repeat(numPlayers.nPlayers){
            board.addCard(deck.drawCard())
        }
    }

    override fun getCurrentPlayerHand(): Hand<FoodCard> {
        return hands[currentPlayer]
    }

    override fun drawCardFromBoardToCurrentPlayerHand(card : FoodCard){
        board.removeCard(card)
        hands[currentPlayer].addCard(card)
    }

    override fun drawCardFromBoardToCurrentPlayerHand(index : Int){
        val card : FoodCard = board.removeCardWithPosition(index)
        hands[currentPlayer].addCard(card)
    }

    fun buildDeck() : FoodCardsDeck =
            deck.apply { buildInitialDeck(numPlayers)}


    fun buildHands(){
        repeat(numPlayers.nPlayers){
            hands.add(Hand())
        }
    }

}