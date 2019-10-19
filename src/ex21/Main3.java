package ex21;

import java.util.concurrent.ThreadLocalRandom;

public class Main3 {

   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d) {

	// Add a sequence of addRow operations with short random naps.
        for (int i=0; i<20; i++){
            d.addRow("AAAAA" + i);
            d.addRow("BBBBB" + i);
            int nap_num = ThreadLocalRandom.current().nextInt(300, 500);
            nap(nap_num);
        }

   }

    private static void deleteProc(HighLevelDisplay d) {
	
	// Add a sequence of deletions of row 0 with short random naps.
        for(int i=0; i<20; i++){
            d.deleteRow(0);
            int nap_num = ThreadLocalRandom.current().nextInt(1200, 2000);
            nap(nap_num);
        }
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();

	new Thread () {
	    public void run() {
		addProc(d);
	    }
	}.start();


	new Thread () {
	    public void run() {
		deleteProc(d);
	    }
	}.start();

    }
}