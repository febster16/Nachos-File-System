package nachos.proj1;

import java.util.Vector;

import nachos.threads.KThread;
import nachos.threads.ThreadQueue;

public class MyQueue extends ThreadQueue{

	private Vector<KThread> list;
	
	public MyQueue() {
		list = new Vector<>();
	}

	@Override
	public void waitForAccess(KThread thread) {
		list.add(thread);
	}

	@Override
	public KThread nextThread() {
		if (list.isEmpty()) {
			return null;
		}
		
		return list.remove(0);
	}

	@Override
	public void acquire(KThread thread) {}

	@Override
	public void print() {}

}
