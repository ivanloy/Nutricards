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
            //TODO Vegetable and cereal
        }

        fun calculateDairyPoints(amount : Int){
            return amount
        }

        fun calculateMeatAndFishPoints(meatAmount : Int, fishAmount : Int){
            var ret : Int = 0;
            if(meatAmount >= 3) ret -= 4
            if(fishAmount >= 3) ret -= 4
            if(meatAmount < 3 && fishAmount < 3){ //TODO exclusive, legibility
                if(meatAmount == 2 && fishAmount == 2) ret += 12
                else ret += 6
            }
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