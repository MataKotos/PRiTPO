package lr1pt2;

import java.util.*;
import java.io.*;

public class CartDealer {

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int t = Integer.parseInt(br.readLine());
	        br.readLine();

	        for (int i = 0; i < t; i++) {
	            if (i > 0) {
	                System.out.println();
	            }
	            solve(br);
	        }
	    }

	    static void solve(BufferedReader br) throws IOException {
	        int n = Integer.parseInt(br.readLine()); 
	        int[][] shuffles = new int[n][52];

	        for (int i = 0; i < n; i++) {
	            String line = br.readLine();
	            String[] shuffleValues = line.split(" ");
	            for (int j = 0; j < 52; j++) {
	                shuffles[i][j] = Integer.parseInt(shuffleValues[j]) -1;
	            }
	        }

	        List<Integer> sequence = new ArrayList<>();
	        String line;
	        while ((line = br.readLine()) != null && !line.isEmpty()) {
	            sequence.add(Integer.parseInt(line) - 1);
	        }

	        List<String> deck = createDeck();

	        for (int shuffleIndex : sequence) {
	            deck = applyShuffle(deck, shuffles[shuffleIndex]);
	        }

	        for (String card : deck) {
	            System.out.println(card);
	        }
	    }

	    static List<String> createDeck() {
	        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

	        List<String> deck = new ArrayList<>();
	        for (String suit : suits) {
	            for (String rank : ranks) {
	                deck.add(rank + " of " + suit);
	            }
	        }
	        return deck;
	    }

	    static List<String> applyShuffle(List<String> deck, int[] shuffle) {
	        List<String> newDeck = new ArrayList<>(52);
	        for (int i = 0; i < 52; i++) {
	            newDeck.add(null);
	        }

	        for (int i = 0; i < 52; i++) {
	            newDeck.set(shuffle[i], deck.get(i));
	        }
	        return newDeck;
	    }
	}
