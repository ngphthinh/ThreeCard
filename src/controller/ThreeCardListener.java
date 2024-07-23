package controller;

import view.ThreeCardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * author: ngphthinh
 * purpose: listener window
 * copyright : 22/07/2024
 */
public class ThreeCardListener implements ActionListener {

	private ThreeCardView threeCardView;

	public ThreeCardListener(ThreeCardView threeCardView) {
		this.threeCardView = threeCardView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
		if (command.equals("Show cards")) {
			this.threeCardView.showCard();
		}
		if (command.equals("New game")) {
			this.threeCardView.dispose();
			this.threeCardView = new ThreeCardView();
		}
	}
}
