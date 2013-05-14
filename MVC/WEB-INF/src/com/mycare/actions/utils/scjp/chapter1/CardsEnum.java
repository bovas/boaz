package com.mycare.actions.utils.scjp.chapter1;

import java.util.ArrayList;
import java.util.List;

public class CardsEnum{
	public enum Rank {
		White,black,green,yello,gray
	}
	public enum Suit{spad,clever,diamonds,ace};
	private final Rank rank;
    private final Suit suit;
    
    private CardsEnum(Rank rank, Suit suit) {
    	System.out.println("Calling constructor...");
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() { return rank; }
    public Suit suit() { return suit; }
    public String toString() { System.out.println("calling to string"); return rank + " of " + suit; }

    private static final List<CardsEnum> protoDeck = new ArrayList<CardsEnum>();

    // Initialize prototype deck
    static {
    	System.out.println("Calling static...");
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                protoDeck.add(new CardsEnum(rank, suit));
    }

    public static ArrayList<CardsEnum> newDeck() {
        return new ArrayList<CardsEnum>(protoDeck); // Return copy of prototype deck
    }
    public static void main(String[] args) {
		
	}
	public Object values() {		
		return null;
	}
}