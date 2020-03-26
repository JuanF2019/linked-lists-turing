package model;
import java.io.*;

public class NodeManager {	
	private Node C0;//Beginning
	private Node C1;//Middle
	private Node C2;//End
	private int length;
	private BufferedReader br;
	private BufferedWriter bw;
	
	/**
	 * Constructor for class NodeManager.
	 */
	public NodeManager() {
		length = 0;
	}
	/**
	 * Reads the input file "in_turing.txt" and executes its instructions, writing in the output file "out_turing.txt".<br>
	 * <b>PRE: </b>The instructions are supposed to be written correctly.<br>
	 * @throws IOException If there is a problem reading the file or writing in the output file.
	 */
	public void readInput() throws IOException {		
		br = new BufferedReader(new FileReader("data//in_turing.txt"));
		bw = new BufferedWriter(new FileWriter("data//out_turing.txt"));
		
		String line = br.readLine();		
		while(line != null) {
			length = 0;
			C0 = null;
			C1 = null;
			C2 = null;
			char[] charLine = line.toCharArray();			
			for(int i = 0 ; i < charLine.length ; i += (charLine[i+1] == '1')? 3:2) {
				switch(charLine[i]) {
				case '0'://C0
					
					switch(charLine[i+1]) {
					case '0'://Read
						readC0();						
					break;
					case '1'://Add
						addC0(new Node(charLine[i+2]));
						
					break;
					case '2'://Remove
						removeC0();						
					break;
					}	
					
				break;
				case '1'://C1
					
					switch(charLine[i+1]) {
						case '0'://Read
							readC1();
						break;
						case '1'://Add
							addC1(new Node(charLine[i+2]));													
						break;
						case '2'://Remove
							removeC1();
						break;
						
					}									
				break;
				case '2'://C2
					
					switch(charLine[i+1]) {
					case '0'://Read
						readC2();						
					break;
					
					case '1'://Add
						addC2(new Node(charLine[i+2]));					
					break;
					
					case '2'://Remove
						removeC2();
					break;
					}
					
				break;
				}				
			}	
			
			line = br.readLine();
			
		}	
		
		br.close();
		bw.close();		
	}
	/**
	 * Reads the letter of the node in C0 and writes in the output file "#" if C0 points to null.<br>
	 * @throws IOException If there is a problem writing in the output file.
	 */
	private void readC0() throws IOException{
		if(C0 != null) {
			bw.write(C0.getLetter());	
			bw.newLine();
		}
		else {
			bw.write("#");		
			bw.newLine();
		}
	}
	/**
	 *Reads the letter of the node in C1 and writes in the output file "#" if C1 points to null.<br>
	 * @throws IOException If there is a problem writing in the output file.
	 */
	private void readC1() throws IOException{
		if(C1 != null) {
			bw.write(C1.getLetter());
			bw.newLine();
		}
		else {
			bw.write("#");
			bw.newLine();
		}
	}
	/**
	 *Reads the letter of the node in C2 and writes in the output file "#" if C2 points to null.<br>
	 * @throws IOException If there is a problem writing in the output file.
	 */
	private void readC2() throws IOException{
		if(C2 != null) {
			bw.write(C2.getLetter());
			bw.newLine();
		}
		else {
			bw.write("#");
			bw.newLine();
		}
	}
	
	/**
	 * Adds a new Node in C0 shifting the rest of the Nodes in the list.<br>
	 * @param n Node to add.
	 * <b>POS: </b> Updates C0 and C1 referenced nodes.	
	 */
	public void addC0(Node n){
		if(C0 != null) {	
			n.setNextNode(C0);
			C0.setPreNode(n);
			C0 = n;
			if(length%2 != 0) {								
				C1 = C1.getPreNode();
			}							
		}
		else {
			C0 = n;
			C1 = n;
			C2 = n;
		}
		length++;
	}
	/**
	 * Adds a new Node in C1 shifting the rest of the Nodes in the list.<br>
	 * <b>POS: </b> Updates C1 referenced node.
	 * @param n Node to add.	
	 */
	public void addC1(Node n){
		if(C1 != null) {
			if(length > 1) {
				if(length%2 ==0) {
					n.setNextNode(C1.getNextNode());
					n.setPreNode(C1);
					C1.getNextNode().setPreNode(n);
					C1.setNextNode(n);
					C1 = n;										
				}
				else {
					n.setNextNode(C1);
					n.setPreNode(C1.getPreNode());
					C1.getPreNode().setNextNode(n);
					C1.setPreNode(n);
					C1 = n;										
				}								
			}
			else {
				n.setNextNode(C2);
				C2.setPreNode(n);
				C0 = n;
				C1 = n;									
			}
		}
		else {
			C0 = n;
			C1 = n;
			C2 = n;
		}
		length++;
	}
	/**
	 * Adds a new Node in C1 shifting the rest of the Nodes in the list.<br>
	 * <b>POS: </b> Updates C1 and C2 referenced nodes.
	 * @param n Node to add.	
	 */
	public void addC2(Node n){
		if(C2 != null) {	
			n.setPreNode(C2);
			C2.setNextNode(n);
			C2 = n;
			if(length%2 == 0) {
				C1 = C1.getNextNode();
			}							
		}
		else {
			C0 = n;
			C1 = n;
			C2 = n;
		}
		length++;
	}
	
	/**
	 * Removes the node in C0 shifting the rest of the Nodes in the list.<br>
	 * <b>POS: </b> Updates C0 and C1 referenced nodes and C2 when necessary.
	 */
	public void removeC0(){
		if(C0 != null) {
			if(C0.getNextNode() !=null) {
				C0 = C0.getNextNode();
				C0.setPreNode(null);
				if(length%2 == 0) {
					C1 = C1.getNextNode();
				}
			}
			else {
				C0 = null;
				C1 = null;
				C2 = null;
			}
			length--;
		}
	}
	/**
	 * Removes the node in C1 shifting the rest of the Nodes in the list.<br>
	 * <b>POS: </b> Updates C1 referenced node and C0 and C2 when necessary.
	 */
	public void removeC1(){
		if(C1 != null) {							
			if(C1.getNextNode() != null) {
				if(length > 2) {
					C1.getPreNode().setNextNode(C1.getNextNode());
					Node auxNode = C1;
					if(length%2 != 0) {
						C1 = C1.getPreNode();
					}
					else {
						C1 = C1.getNextNode();
					}
					auxNode.getNextNode().setPreNode(auxNode.getPreNode());		
					auxNode = null;
				}
				else {
					C0 = C2;
					C1 = C2;
					C2.setPreNode(null);
				}
			}
			else {
				C0 = null;
				C1 = null;
				C2 = null;
			}
			length--;
		}
	}
	/**
	 * Removes the node in C2 shifting the rest of the Nodes in the list.<br>
	 * <b>POS: </b> Updates C1 and C2 referenced nodes and C0 when necessary.
	 */
	public void removeC2(){
		if(C2 != null) {
			if(C2.getPreNode() != null) {
				C2 = C2.getPreNode();
				C2.setNextNode(null);
				if(length%2 != 0) {
					C1 = C1.getPreNode();
				}
			}
			else {
				C0 = null;
				C1 = null;
				C2 = null;
			}
			length--;
		}
	}
	
	/**
	 * Returns a reference to the object referenced by C0.<br>
	 * @return Reference to the object referenced by C0.
	 */
	public Node getC0() {
		return C0;
	}
	/**
	 * Returns a reference to the object referenced by C1.<br>
	 * @return Reference to the object referenced by C1.
	 */
	public Node getC1() {
		return C1;
	}
	/**
	 * Returns a reference to the object referenced by C1.<br>
	 * @return Reference to the object referenced by C1.
	 */
	public Node getC2() {
		return C2;
	}
	/**
	 * Returns the length of the list.<br>
	 * @return Length of the list.
	 */
	public int getLength() {
		return length;
	}	
}
