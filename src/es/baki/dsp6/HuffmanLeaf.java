package es.baki.dsp6;

public class HuffmanLeaf {
	private HuffmanLeaf left, right;
	private char letter;
	private int value;
	
	public HuffmanLeaf(HuffmanLeaf left, HuffmanLeaf right, char letter, int value) {
		this.setLeft(left);
		this.setRight(right);
		this.setLetter(letter);
		this.setValue(value);
	}

	public boolean contains(char c) { 
		if (letter == 0) {
			return left.contains(c) || right.contains(c); 
		} else if (letter == c)
			return true;
		return false;
	}
	
	public String getHuffman(char c) {
		if (letter == c) { 
			return "";
		}
		if (left.contains(c)) { 
			return "0" + left.getHuffman(c);
		} else {
			return "1" + right.getHuffman(c);
		}
	}
	
	public HuffmanLeaf getLeft() {
		return left;
	}

	public void setLeft(HuffmanLeaf left) {
		this.left = left;
	}

	public HuffmanLeaf getRight() {
		return right;
	}

	public void setRight(HuffmanLeaf right) {
		this.right = right;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
