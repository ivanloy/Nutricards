package com.ivanloy.nutricards.controllers

import com.ivanloy.nutricards.ds.Hand
import com.ivanloy.nutricards.gamedata.FoodCardTypes
import com.ivanloy.nutricards.gameelements.FoodCard

/**
 * Created by ivanloy on 15/01/19.
 */
class PointsCalculator {

    companion object {

        fun calculateHandPoints(hand : Hand<FoodCard>) : Int {

            var cardMap : HashMap<FoodCardTypes, Int> = getCardsMap(hand)
            var total : Int = 0

            return total

        }

        fun getCardsMap(hand : Hand<FoodCard>) : HashMap<FoodCardTypes, Int>{

            var cardMap : HashMap<FoodCardTypes, Int> = HashMap()
            for(card in hand){
                var cardTypeAmount : Int? = cardMap[card.type]
                if(cardTypeAmount == null) cardMap.put(card.type, 1)
                else cardMap.put(card.type, cardTypeAmount + 1)
            }
            return cardMap

        }

    }



}