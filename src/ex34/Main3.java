package ex34;

import java.util.concurrent.*;

public class Main3 {
    private Semaphore semaphore = new Semaphore(8);

   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d, Semaphore semaphore) {

	// Add a sequence of addRow operations with short random naps.
        for (int i=0; i<20; i++) {

                semaphore.acquireUninterruptibly();
                d.addRow("AAAAA" + i);
                d.addRow("BBBBB" + i);
                semaphore.release();
                //int nap_num = ThreadLocalRandom.current().nextInt(300, 500);
                //nap(nap_num);
                System.out.println("add inter");
        }

   }

    private static void deleteProc(HighLevelDisplay d, Semaphore semaphore) {
	
	// Add a sequence of deletions of row 0 with short random naps.
        for(int i=0; i<20; i++){

                System.out.println("wait");
                semaphore.acquireUninterruptibly();
                System.out.println("OK");
                d.deleteRow(0);
                semaphore.release();
                //int nap_num = ThreadLocalRandom.current().nextInt(1200, 2000);
                //nap(nap_num);
        }
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();
	Semaphore s = new Semaphore(1);

	new Thread () {
	    public void run() {
		addProc(d, s);
	    }
	}.start();


	new Thread () {
	    public void run() {
		deleteProc(d, s);
	    }
	}.start();

    }
}