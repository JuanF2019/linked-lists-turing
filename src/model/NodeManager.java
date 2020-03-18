package model;
import java.io.*;

public class NodeManager {	
	private Node C0;//Beginning
	private Node C1;//Middle
	private Node C2;//End
	private int length;
	
	public NodeManager() {
		length = 0;
	}
	
	public void readInput() throws IOException {		
		BufferedReader br = new BufferedReader(new FileReader("data//in_turing.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data//out_turing.txt"));
		
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
						if(C0 != null) {
							bw.write(C0.getLetter());	
							bw.newLine();
						}
						else {
							bw.write("#");		
							bw.newLine();
						}
					break;
					case '1'://Add
						Node n = new Node(charLine[i+2]);
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
					break;
					case '2'://Remove
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
					break;
					}					
				break;
				case '1'://C1
					switch(charLine[i+1]) {
						case '0'://Read
							if(C1 != null) {
								bw.write(C1.getLetter());
								bw.newLine();
							}
							else {
								bw.write("#");
								bw.newLine();
							}
						break;
						case '1'://Add			
							Node n = new Node(charLine[i+2]);
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
						break;
						case '2'://Remove
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
						break;
					}									
				break;
				case '2'://C2
					switch(charLine[i+1]) {
					case '0'://Read
						if(C2 != null) {
							bw.write(C2.getLetter());
							bw.newLine();
						}
						else {
							bw.write("#");
							bw.newLine();
						}
					break;
					case '1'://Add
						Node n = new Node(charLine[i+2]);
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
					break;
					case '2'://Remove
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

	public Node getC0() {
		return C0;
	}

	public Node getC1() {
		return C1;
	}

	public Node getC2() {
		return C2;
	}

	public int getLength() {
		return length;
	}	
}
