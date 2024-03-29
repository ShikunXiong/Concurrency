package ex32;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class VectorTest {

    private boolean running = true;
    private CopyOnWriteArrayList<String> people = new CopyOnWriteArrayList<String>();

    private void addPerson() {
	people.add(RandomUtils.randomString());
    }

    private String getLast() {
	int lastIndex = people.size() - 1;
	if (lastIndex >= 0)
	    return people.get(lastIndex);
	else return "";
    }

    private void deleteLast() {
	int lastIndex = people.size() - 1;
	if (lastIndex >= 0)
	    people.remove(lastIndex);
    }

    public void run() {
	// Start getter thread
	new Thread(new Runnable () {
		public void run() {
		    Thread.currentThread().setName("Getter");
		    while (running) {
			String person = getLast();
			System.out.println("Last: " + person);
			try {
			    Thread.sleep(200);
			} catch (InterruptedException e) {}
		    }
		}
	    }).start();

	// Start deleter thread
	new Thread(new Runnable () {
		public void run() {
		    Thread.currentThread().setName("Deleter");
		    while (running) {
			deleteLast();
			try {
			    Thread.sleep(200);
			} catch (InterruptedException e) {}
		    }
		}
	    }).start();

	// This thread adds entries
	for (int i = 0; i < 100; i++) {
	    addPerson();
	    try {
		Thread.sleep(200);
	    } catch (InterruptedException e) {}
	}
	running = false;
    }

    public static void main(String[] args) {
	VectorTest hm = new VectorTest();
	hm.run();
    }

}
