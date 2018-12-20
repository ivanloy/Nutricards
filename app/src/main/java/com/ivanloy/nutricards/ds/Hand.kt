package com.ivanloy.nutricards.ds

class Hand<T>: Cloneable {

    var handList: MutableList<T> = ArrayList()
        private set

    constructor(hand : Hand<T>){
        this.handList = hand.handList.toMutableList();
    }

    constructor(vararg cards : T){
        handList = cards.toMutableList()
    }

    fun peekCard(index : Int) : T{
        return handList[index]
    }

    fun removeCard(vararg cards: T){
        cards.forEach {
            handList.remove(it)
        }
    }

    fun size(): Int {
        return handList.size
    }

    fun addCard(vararg cards: T) {
        handList.addAll(cards.toList())
    }

    fun addCard(hand: Hand<T>) {
        handList.addAll(hand.handList)
    }

}