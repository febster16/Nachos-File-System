package nachos.proj1;

import java.util.Vector;

import nachos.machine.FileSystem;
import nachos.machine.Machine;
import nachos.machine.OpenFile;
import nachos.machine.Timer;
import nachos.threads.KThread;

public class MainSystem {
	
	private MyConsole console;
	private int input;
	private FileSystem fs;
	private Timer timer;
	private Vector<Cake> cakeList;
	
	public void cakeMenu() {
		do {
			console.writeln("Cake Store\n=============");
			console.writeln("1. Add Cake");
			console.writeln("2. View Cake(s)");
			console.writeln("3. Deliver");
			console.writeln("4. Exit");
			console.write(">> ");
			input = console.readInt();
		
			switch (input) {
			case 1:
				addCake();
				break;

			case 2:
				viewCake();
				break;

			case 3:
				deliver();
				break;
			}
		} while (input != 4);
		
		console.writeln("\nTime: " + (timer.getTime() / 10000000));	
		console.writeln("Thank you. Good bye");
	}
	
	private void deliver() {
//		Vector<String> cakes = new Vector<>();
//		Vector<String> name = new Vector<>();
//		Vector<String> filling = new Vector<>();
//		Vector<String> topping = new Vector<>();
//		
//		String fileName = "CakeList", content;
//		
//		OpenFile ofile = fs.open(fileName, false);
//		if(ofile != null) {
//			byte[] contentBytes = new byte[ofile.length()];
//			ofile.read(contentBytes, 0, contentBytes.length);
//			content = new String(contentBytes);
//			
//			String[] tokens = content.split("\\r?\\n");
//
//			for (String t : tokens) {
//				cakes.add(t);
//			}
//			
//			for (String cake : cakes) {
//				String[] parts = cake.split("#");
//				name.add(parts[0]);
//				filling.add(parts[1]);
//				topping.add(parts[2]);
//			}
//			
//			for (int i = 0; i < name.size(); i ++) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				console.writeln("Cake Name: " + name.elementAt(i));
//				console.writeln("Cake Filling: " + filling.elementAt(i));
//				console.writeln("Cake Topping: " + topping.elementAt(i));
//				console.writeln("");
//				
//				// clear CakeList.txt after deliver
//				clearFile();
//			}
//			
//		} else {
//			console.writeln("No Cake(s)...");
//		}
//		console.writeln("Press enter to continue...");
//		console.readLine();
		if(cakeList.isEmpty()) {
			console.writeln("No Cake(s)...");
		} else {
			while(!cakeList.isEmpty()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				new KThread(cakeList.remove(0)).fork();
			}
		}
		console.writeln("Press enter to continue...");
		console.readLine();
	}

	private void viewCake() { 
//		Vector<String> cakes = new Vector<>();
//		Vector<String> name = new Vector<>();
//		Vector<String> filling = new Vector<>();
//		Vector<String> topping = new Vector<>();
//		
//		String fileName = "CakeList", content;
//		
//		OpenFile ofile = fs.open(fileName, false);
//		if(ofile != null) {
//			byte[] contentBytes = new byte[ofile.length()];
//			ofile.read(contentBytes, 0, contentBytes.length);
//			content = new String(contentBytes);
//			
//			String[] tokens = content.split("\\r?\\n");
//
//			for (String t : tokens) {
//				cakes.add(t);
//			}
//			
//			for (String cake : cakes) {
//				String[] parts = cake.split("#");
//				name.add(parts[0]);
//				filling.add(parts[1]);
//				topping.add(parts[2]);
//			}
//			
//			for (int i = 0; i < name.size(); i ++) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				console.writeln("Cake Name: " + name.elementAt(i));
//				console.writeln("Cake Filling: " + filling.elementAt(i));
//				console.writeln("Cake Topping: " + topping.elementAt(i));
//				console.writeln("");
//				
//			}
//			
//		} else {
//			console.writeln("No Cake(s)...");
//		}
		
		if(cakeList.isEmpty()) {
			console.writeln("No Cake(s)...");
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < cakeList.size(); i++) {
				new KThread(cakeList.get(i)).fork();
			}
		}
		console.writeln("Press enter to continue...");
		console.readLine();
	}
	
	public void clearFile() {
		String fileName = "CakeList";
		String content;
		content = "";
		OpenFile ofile = fs.open(fileName, true);
		if(ofile != null) {
			byte[] contentBytes = content.getBytes();
			ofile.write(ofile.length(), contentBytes, 0, contentBytes.length);
		} else { 
			console.writeln("File not found.");
		}
	}
	

	private void addCake() {
		String name = "", filling = "", topping = "";
		boolean containsNumber;
		do {
			containsNumber = false;
			console.write("Cake name [3..50 characters](inclusive)(Letters and spaces only): ");
			name = console.readLine();
			char[] chars = name.toCharArray();
		      for(char c : chars){
		         if(Character.isDigit(c) || !Character.isLetter(c)){
		        	 if(Character.isWhitespace(c)) {
		        		 containsNumber = false;
		        	 } else {
			        	 containsNumber = true;
			        	 break; 
		        	 }
		         }
		    }
		} while (name.length() < 3 || name.length() > 50 || containsNumber == true);
		
		do {
			console.write("Cake filling [ Strawberry | Chocolate ](Case Sensitive): ");
			filling = console.readLine();
		} while (!filling.equals("Strawberry") && !filling.equals("Chocolate"));
		
		do {
			console.write("Cake topping [ Fresh Strawberries | Chocolate Chips | Banana Slice ](Case Sensitive): ");
			topping = console.readLine();
		} while (!topping.equals("Fresh Strawberries") && !topping.equals("Chocolate Chips") && !topping.equals("Banana Slice"));
		
		appendFile(name, filling, topping);
		cakeList.add(new Cake(name, filling, topping));
		console.writeln("Cake has been successfully inserted...");
		console.writeln("Press enter to continue...");
		console.readLine();
		
	}
	
	public void appendFile(String name, String filling, String topping) {
		String fileName = "CakeList";
		String content;
		content = name + "#" +  filling + "#" + topping + "\n";
		OpenFile ofile = fs.open(fileName, false);
		if(ofile != null) {
			byte[] contentBytes = content.getBytes();
			ofile.write(ofile.length(), contentBytes, 0, contentBytes.length);
		} else { 
			console.writeln("File not found.");
		}
	}

	public MainSystem() {
		console = new MyConsole();
		timer = Machine.timer();
		fs = Machine.stubFileSystem();
		cakeList = new Vector<Cake>();
		cakeMenu();
	}
	
}
