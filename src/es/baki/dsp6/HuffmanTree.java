package es.baki.dsp6;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HuffmanTree {
	private HuffmanLeaf parent;

	public HuffmanTree(CharFrequencyList cfl) { 
		if (cfl.size() < 2) {
			return;
		}
		CharFrequencyNode node1 = cfl.getList().get(0);
		CharFrequencyNode node2 = cfl.getList().get(1);

		cfl.getList().remove(0);
		cfl.getList().remove(0);

		HuffmanLeaf leaf1 = new HuffmanLeaf(null, null, node1.getLetter(), node1.getCount());
		HuffmanLeaf leaf2 = new HuffmanLeaf(null, null, node2.getLetter(), node2.getCount());

		if (leaf1.getValue() > leaf2.getValue())
			parent = new HuffmanLeaf(leaf2, leaf1, (char) 0, leaf1.getValue() + leaf2.getValue());
		else
			parent = new HuffmanLeaf(leaf1, leaf2, (char) 0, leaf1.getValue() + leaf2.getValue());
		
		while (cfl.size() != 0) {
			CharFrequencyNode node = cfl.getList().get(0); 
			HuffmanLeaf newLeaf = new HuffmanLeaf(null, null, node.getLetter(), node.getCount());
			cfl.getList().remove(0);
			
			if (newLeaf.getValue() > parent.getValue()) {
				parent = new HuffmanLeaf(parent, newLeaf, (char) 0, newLeaf.getValue() + parent.getValue());
			} else {
				parent = new HuffmanLeaf(newLeaf, parent, (char) 0, newLeaf.getValue() + parent.getValue());
			}
		}
	}

	public String encode(String inputString) { 
		String output = "";
		for (char c : inputString.toCharArray())
			output += parent.getHuffman(c);
		return output;
	}
	
	public String decode(String encodedString) {
		String decoded = ""; 
		HuffmanLeaf location = parent; 
		for (char c : encodedString.toCharArray()) {
			if (c == ' ')
				continue;
			if (c == '1') {
				location = location.getRight();
			} else if (c == '0') {
				location = location.getLeft();
			} 
			if (location.getLetter() != 0) {
				decoded += location.getLetter();
				location = parent;
			}
		}
		
		return decoded;
	}
	
	public HuffmanTree(HuffmanLeaf hl) {
		this.parent = hl;
	}

	public static void main(String...strings) throws IOException {
		Scanner console = new Scanner(System.in);
		System.out.print("Input filename: ");;
		String inputFilename = console.nextLine();
		System.out.print("Encode filename: ");;
		String encodeFilename = console.nextLine();
		System.out.print("Decode filename: ");;
		String decodeFilename = console.nextLine();
		console.close();
		
		File encodeFile = new File(encodeFilename);
		File decodeFile = new File(decodeFilename);
		
		CharFrequencyList cfl = new CharFrequencyList();
		
		Scanner inputFileScanner = new Scanner(new File(inputFilename));
		String input = "";
		while (inputFileScanner.hasNext()) {
			input += inputFileScanner.nextLine() + String.format("%n");
		}
		inputFileScanner.close();
		for (char c : input.toCharArray()) {
			cfl.addLetter(c);
		}
		HuffmanTree tree = new HuffmanTree(cfl);
		
		if (!encodeFile.exists())
			encodeFile.createNewFile();
		String encoded = tree.encode(input);
		PrintWriter encodedOut = new PrintWriter(encodeFile);
		encodedOut.print(encoded);
		encodedOut.close();
		
		if (!decodeFile.exists())
			decodeFile.createNewFile();
		PrintWriter decodedOut = new PrintWriter(decodeFile);
		String decoded = tree.decode(encoded);
		decodedOut.print(decoded);
		decodedOut.close();
				
	}
}
