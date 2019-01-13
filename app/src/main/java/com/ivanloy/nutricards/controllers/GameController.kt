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

    override fun getDeckSize(): Int {
        return deck.size()
    }

    override fun getBoardCard(index: Int) : FoodCard {
        return board.peekCardOrDefault(index, FoodCard())
    }

    override fun nextPlayer(){
        if(currentPlayer == numPlayers.nPlayers - 1) currentPlayer = 0 else currentPlayer++
    }

    override fun drawCardToCurrentPlayerHand() : Boolean{
        var ret = false
        if(!deck.isEmpty) {
            hands[currentPlayer]
                    .addCard(deck.drawCard())
            ret = true
        }
        return ret
    }

    override fun fillBoard() : Boolean{
        var ret = false
        if(!deck.isEmpty) {
            board = Hand()
            repeat(numPlayers.nPlayers) {
                if (!deck.isEmpty) board.addCard(deck.drawCard())
            }
            ret = true
        }
        return ret
    }

    override fun getCurrentPlayerHand(): Hand<FoodCard> {
        return hands[currentPlayer]
    }

    override fun drawCardFromBoardToCurrentPlayerHand(card : FoodCard){
        board.removeCard(card)
        hands[currentPlayer].addCard(card)
    }

    override fun drawCardFromBoardToCurrentPlayerHand(index : Int){
        val card : FoodCard = board.removeCardWithPositionAndReplacement(index, FoodCard())
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