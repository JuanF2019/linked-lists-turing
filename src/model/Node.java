package model;

public class Node {
	private char letter;
	private Node preNode;
	private Node nextNode;
	
	public Node(char l) {
		letter = l;
	}

	public char getLetter() {
		return letter;
	}

	public Node getPreNode() {
		return preNode;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public void setPreNode(Node preNode) {
		this.preNode = preNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	public String toString() {
		return "letter = " + letter;
	}
}
