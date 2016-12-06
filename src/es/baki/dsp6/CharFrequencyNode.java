package es.baki.dsp6;

public class CharFrequencyNode {
	private char letter;
	private int count;
	
	public CharFrequencyNode(char c) {
		letter = c;
		count = 1;
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	} 
	
	public int getCount() {
		return count;
	}
	
	public void increment() {
		count ++;
	}
	
	
}
