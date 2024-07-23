package model;

/*
 * author: ngphthinh
 * purpose: Make a card
 * copyright : 22/07/2024
 */
public class CardModel {
	private String value;
	private String type;

	public CardModel(String value, String type) {
		this.value = value;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		if ("AJQK".contains(value)) {
			if (value.equals("A")) {
				return 1;
			} else {
				return 10;
			}
		} else return Integer.parseInt(value);
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value + "-" + type;
	}

	public String getImagePath() {
		return "/cards/" + this.toString() + ".png";
	}
}
