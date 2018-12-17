package com.ivanloy.nutricards.ds;

public class Hand<T> implements Cloneable{

    private T[] hand;

    public Hand() { hand = null; }

    /**
     * @param cards The initial cards of the hand
     */
    public Hand(T... cards) {

        hand = cards;

    }

    public int size() { return hand == null ? 0 : hand.length; }

    public T[] getHand() { return hand; }

    public void addCard(T... cards) {

        T[] hand_;

        if(hand != null) {

            hand_ = (T[]) new Object[hand.length + cards.length];

            int i;
            for(i = 0; i < hand.length; i++) hand_[i] = hand[i];
            for(; i < hand_.length; i++) hand_[i] = cards[i - hand.length];

        }else {

            hand_ = (T[])new Object[cards.length];
            for(int i = 0; i < cards.length; i++) hand_[i] = cards[i];

        }

        hand = hand_;

    }

    public void addCard(Hand hand) {

        addCard((T[])hand.getHand());

    }

    public Hand clone() {

        return new Hand(hand);

    }

}