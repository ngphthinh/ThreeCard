package model;

import java.util.ArrayList;
import java.util.Random;
/**
 * author: ngphthinh
 * purpose: Make a deck cards
 * copyright : 23/07/2024
 */
public class DeckCardModel {
	private ArrayList<CardModel> deckCard;

	public DeckCardModel() {
		this.deckCard = new ArrayList<>();
		this.initialize();
		this.shuffleDeck();
	}

	// shuffe deck card
	private void shuffleDeck() {
		Random random = new Random();
		for (int i = 0; i < deckCard.size(); i++) {
			int j = random.nextInt(deckCard.size());
			CardModel currCard = deckCard.get(i);
			CardModel randomCard = deckCard.get(j);
			deckCard.set(i, randomCard);
			deckCard.set(j, currCard);
		}
		System.out.println("Finished shuffling the cars:");
		toString();
	}

	// init deckCard
	private void initialize() {
		String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] type = {"H", "D", "S", "C"};
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < type.length; j++) {
				CardModel card = new CardModel(values[i], type[j]);
				this.deckCard.add(card);
			}
		}
		System.out.println("Finished initialize the cards: ");
	}

	public ArrayList<CardModel> getDeckCard() {
		return deckCard;
	}

	public void setDeckCard(ArrayList<CardModel> deckCard) {
		this.deckCard = deckCard;
	}

	@Override
	public String toString() {
		System.out.println("Show deck of cards");
		for (CardModel c : this.deckCard) {
			System.out.print(c + " ");
		}
		System.out.println();
		return "\n";
	}

	public int dealCardToPlayer() {
		int res = 0;
		System.out.println("Player: ");
		for (int i = 0; i < 3; i++) {
			CardModel card = this.deckCard.get(i);
			System.out.println(card);
			res += card.getValue();
		}
		return res;
	}

	public int dealCardToComputer() {
		int res = 0;
		System.out.println("Computer: ");
		for (int i = 3; i < 6; i++) {
			CardModel card = this.deckCard.get(i);
			System.out.println(card);
			res += card.getValue();
		}
		return res;
	}

	public void removeCards() {
		for (int i = 0; i < 3; i++)
			this.deckCard.remove(0);
	}

	public void checkWin() {

		int player = dealCardToPlayer();
		if (player != 30) {
			player %= 10;
		}
		// remove three cards
//		removeCards();

		int computer = dealCardToComputer();
		if (computer != 30) {
			computer %= 10;
		}
		if (player == computer) {
			System.out.println("Draw");
		} else if (player > computer) {
			System.out.println("You Win");
		} else
			System.out.println("You Lose");
	}


}
