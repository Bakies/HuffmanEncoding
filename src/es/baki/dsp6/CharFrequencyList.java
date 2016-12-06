package es.baki.dsp6;

import java.util.ArrayList;

public class CharFrequencyList {
	private ArrayList<CharFrequencyNode> list;
	
	public CharFrequencyList() {
		list = new ArrayList<>();
	}
	
	public ArrayList<CharFrequencyNode> getList() {
		return list;
	}
	
	public void addLetter(char c) {
		for (int x = 0; x < list.size(); x ++) {
			CharFrequencyNode node = list.get(x);
			if (node.getLetter() == c) {
				node.increment();
				checkOrder(x); 
				return;
			}
		}
		
		CharFrequencyNode newNode = new CharFrequencyNode(c);
		list.add(0, newNode);
	}
			
	private void checkOrder(int index) {
		if (index + 1 == list.size())
			return;
		CharFrequencyNode node = list.get(index);
		CharFrequencyNode nextNode = list.get(index + 1);
		
		if (node.getCount() > nextNode.getCount()) {
			list.set(index, nextNode);
			list.set(index + 1, node);
			
		}
			checkOrder(index + 1);
	}

	public int size() {
		return list.size();
	}
	
	public String toString() {
		String ret = "";
		for (CharFrequencyNode node : list) {
			ret += String.format("%s %d%n",node.getLetter(), node.getCount());
		}
		return ret;
	}
}
