package ex33;

import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private LinkedBlockingQueue<Message> queue;
    private int id;

    public Consumer(LinkedBlockingQueue<Message> q, int n) {
	queue = q;
	id = n;
    }
    
    public void run() {
	Message msg = null;
	int count = 0;
	do {
	    try {
		msg = queue.take(); // Get a message from the queue
		count++;
		RandomUtils.print("Consumed " + msg.get(), id);
		Thread.sleep(RandomUtils.randomInteger());
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	} while (msg.get() != "stop");
	// Don't count the "stop" message
	count--;
	RandomUtils.print("Messages received: " + count, id);
    }
}
