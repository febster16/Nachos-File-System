package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class MyConsole {
	private SerialConsole sercon;
	private Character chara;
	private Semaphore sema;
	
	
	public MyConsole() {
		sercon = Machine.console();
		sema = new Semaphore(0);
		
		Runnable receiveHandler = new Runnable() {
			
			@Override
			public void run() {
				chara = (char)sercon.readByte();
				sema.V();
			}
		};
		
		Runnable sendHandler = new Runnable() {
			
			@Override
			public void run() {
				sema.V();
			}
		};
		
		sercon.setInterruptHandlers(receiveHandler, sendHandler);
	}
	
	public String readLine() {
		String newString = "";
		
		do {
			sema.P();
			if(chara != '\n') {
				newString += chara;
			}
		}while(chara != '\n');
		

		return newString;
	}
	
	public Integer readInt() {
		Integer newInteger = 0;
		
		try {
			newInteger = Integer.parseInt(readLine());
		} catch (Exception e) {
			newInteger = 0;
		}
		
		return newInteger;
	}

	public void write(String writeString) {
		for(int i = 0; i < writeString.length(); i++) {
			sercon.writeByte(writeString.charAt(i));
			sema.P();
		}
	}
	
	public void writeln(String writeString) {
		write(writeString + '\n');
	}
	
}
