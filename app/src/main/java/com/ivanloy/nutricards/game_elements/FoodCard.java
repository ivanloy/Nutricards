package com.ivanloy.nutricards.game_elements;

public class FoodCard {

    public FoodCardTypes type;

    public FoodCard(){
        type = FoodCardTypes.BLANK;
    }

    public FoodCard(FoodCardTypes type){
        this.type = type;
    }

    public FoodCardTypes getType() {
        return type;
    }

}
