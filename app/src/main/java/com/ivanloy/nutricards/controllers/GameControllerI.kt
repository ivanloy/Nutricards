package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gameelements.FoodCard

/**
 * Created by ofunes on 20/12/18.
 */
interface GameControllerI {

    fun nextPlayer()
    fun drawCardToCurrentPlayerHand() : Boolean
    fun fillBoard() : Boolean
    fun getCurrentDeckSize() : Int
    fun drawCardFromBoardToCurrentPlayerHand(card : FoodCard)
    fun drawCardFromBoardToCurrentPlayerHand(index : Int)
    fun getCurrentPlayerHand() : Hand<FoodCard>
    fun getBoardCard(index : Int) : FoodCard
    fun calculateCurrentPlayerScore() : Int
    fun getCardAmountOfType(type : FoodCardTypes) : Int

}