package view;

import controller.ThreeCardListener;
import model.CardModel;
import model.DeckCardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * author :ngphthinh
 * purpose : display cards of three-cards
 * date : 23/07/2024
 */
public class ThreeCardView extends JFrame {
	//	creating panel game
	//	init button
	private JPanel buttonPanel = new JPanel();
	private JButton newGameButton = new JButton("New game");
	private JButton showCardButton = new JButton("Show cards");
	private boolean display = false;
	//	creating deck cards
	private DeckCardModel deckCardModel;
	//	Draw panel game
	private JPanel gamePanel = new JPanel() {
		int cardWidth = 110;
		int cardHeight = 154;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int player = -1;
			int computer = -1;
			try {
//				draw the computer's cards
				if (display) {
					for (int i = 3; i < 6; i++) {
						CardModel card = deckCardModel.getDeckCard().get(i);
						Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
						computer += card.getValue();
						g.drawImage(cardImg, 30 + (cardWidth + 5) * (i - 3), 20, cardWidth, cardHeight, null);
					}
				} else {
					for (int i = 0; i < 3; i++) {
						Image hiddenCardImg = new ImageIcon(getClass().getResource("/cards/BACK.png")).getImage();
						g.drawImage(hiddenCardImg, 30 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
					}
				}
//				draw the player's cards
				for (int i = 0; i < 3; i++) {
					CardModel card = deckCardModel.getDeckCard().get(i);
					Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
					player += card.getValue();
					g.drawImage(cardImg, 30 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (display) {
				String message = "";
				if (player != 30) player %= 10;
				if (computer != 30) computer %= 10;
				if (player == computer) {
					message = "Draw";
				} else if (player > computer) {
					message = "You Win";
				} else
					message = "You Lose";
				g.setFont(new Font("Arial", Font.PLAIN, 30));
				g.setColor(Color.white);
				g.drawString(message, 150, 250);
			}
		}
	};

	public ThreeCardView() {
		this.deckCardModel = new DeckCardModel();
		this.initWindow();
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void initWindow() {
		//	window size
		int boardWith = 450;
		int boardHeight = 600;

		this.setTitle("Three Card");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(boardWith, boardHeight);
		this.setLocationRelativeTo(null);
		gamePanel.setBackground(new Color(36, 119, 77));
		gamePanel.setLayout(new BorderLayout());
//		creating ActionListener
		ActionListener actionListener = new ThreeCardListener(this);
		showCardButton.addActionListener(actionListener);
		newGameButton.addActionListener(actionListener);

		this.add(gamePanel);
		newGameButton.setFocusable(false);
		buttonPanel.add(newGameButton);
		showCardButton.setFocusable(false);
		buttonPanel.add(showCardButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public JButton getNewGameButton() {
		return newGameButton;
	}


	public JButton getShowCardButton() {
		return showCardButton;
	}

	public DeckCardModel getDeckCardModel() {
		return deckCardModel;
	}

	public void showCard() {
		this.deckCardModel.checkWin();
		display = true;
		gamePanel.repaint();
	}

	public void newGame() {
		this.deckCardModel = new DeckCardModel();
	}
}
