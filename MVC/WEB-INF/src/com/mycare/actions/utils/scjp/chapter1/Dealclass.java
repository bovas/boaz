package com.mycare.actions.utils.scjp.chapter1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealclass {
	public static void main(String args[]) {
        int numHands = Integer.parseInt(args[0]);
        int cardsPerHand = Integer.parseInt(args[1]);
        List<CardsEnum> deck  = CardsEnum.newDeck();
        Collections.shuffle(deck);
        System.out.println(deck);
        for (int i=0; i < numHands; i++)
            System.out.println(deal(deck, cardsPerHand));
    }

    public static ArrayList<CardsEnum> deal(List<CardsEnum> deck, int n) {
         int deckSize = deck.size();
         List<CardsEnum> handView = deck.subList(deckSize-n, deckSize);
         ArrayList<CardsEnum> hand = new ArrayList<CardsEnum>(handView);
         handView.clear();         
         return hand;
     }
}
