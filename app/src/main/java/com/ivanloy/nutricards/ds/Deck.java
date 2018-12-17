package com.ivanloy.nutricards.ds;

import com.ivanloy.nutricards.game_elements.FoodCard;

public class Deck extends Stequeue<FoodCard> {

    public void putCardOnTop(FoodCard card){
        super.push(card);
    }

    public void putCardOnBottom(FoodCard card){
        super.enqueue(card);
    }

    public FoodCard drawCard(){
        return super.pop();
    }

}
