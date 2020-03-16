package ui;
import java.io.IOException;

import model.NodeManager;

public class Main {
	public static void main(String[] args) {
		NodeManager nm = new NodeManager();		
		long t1 = 0;
		long t2 = 0;
		try {
			t1 = System.currentTimeMillis();
			nm.readInput();
			t2 = System.currentTimeMillis();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}		
		System.out.println("Time: " + (t2-t1) + "ms");
	}
}
