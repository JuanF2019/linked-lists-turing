package model;

public class Node {
	private char letter;
	private Node preNode;
	private Node nextNode;
	
	/**
	 * Constructor for class Node.<br>
	 * @param l Character of the Node.
	 */
	public Node(char l) {
		letter = l;
	}
	
	/**
	 * Returns the Node letter.<br>
	 * @return Letter
	 */
	public char getLetter() {
		return letter;
	}
	/**
	 * Returns a reference to the previous Node.<br>
	 * @return Reference to the previous Node.
	 */
	public Node getPreNode() {
		return preNode;
	}
	
	/**
	 * Returns a reference to the next Node.<br>
	 * @return Reference to the next Node.
	 */
	public Node getNextNode() {
		return nextNode;
	}
	
	/**
	 * Sets the Node letter to the given one.<br>
	 * <b>POS: </b>Updates the Node letter.<br>
	 * @param letter Letter to set.
	 */
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	/**
	 * Sets the preNode referenced Node to the one referenced by the parameter.<br>
	 * <b>POS: </b>Updates the preNode reference.<br>
	 * @param preNode Reference to the new previous Node to set.	 
	 */
	public void setPreNode(Node preNode) {
		this.preNode = preNode;
	}
	
	/**
	 * Sets the nextNode referenced Node to the one referenced by the parameter.<br>
	 * <b>POS: </b> Updates the nextNode reference.<br>
	 * @param nextNode Reference to the new next Node to set.
	 */
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	/**
	 * Returns the Node letter as a String.<br>
	 */
	public String toString() {
		return "letter = " + letter;
	}
}
