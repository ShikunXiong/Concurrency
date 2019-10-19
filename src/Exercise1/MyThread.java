package Exercise1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class MyThread extends Thread implements Runnable{

    @Override
    public void run() {
        this.echo();
    }

    public void echo(){
        while(this.isAlive()) {
            Long name = this.getId();
            String time = new Date().toString();
            System.out.println("Hello World I'm thread<" + name +
                    "> The time is " + time
            );
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.err.println(e);
            }

        }
    }

    public void kill(){
        this.stop();
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<MyThread> list = new ArrayList<>();
        while(true) {
            System.out.println("Here are your options:\r\n");
            System.out.print("a-Create a new Thread\r\n"+
            "b-Stop a given thread(e.g. 'b2' kills Thread 2)\r\n"+
            "c-Stop all Threads and exit this program\r\n");
            if (scanner.hasNext()) {
                input = scanner.nextLine();
            }
            // a
            if (input.equals("a")) {
                MyThread t = new MyThread();
                t.start();
                list.add(t);
            }
            // b
            else if (input.substring(0,1).equals("b")){
                int id  = Integer.parseInt(input.substring(1));
                Iterator<MyThread> iterator = list.iterator();
                boolean flag = false;
                while(iterator.hasNext()){
                    MyThread item = iterator.next();
                    if (item.getId()==id){
                        item.kill();
                        iterator.remove();
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    System.out.println("Thread no found");
                }

            }
            else if (input.equals("c")){
                Iterator<MyThread> iterator = list.iterator();
                while(iterator.hasNext()){
                    MyThread item = iterator.next();
                        item.kill();
                        iterator.remove();
                    }
                System.exit(1);
            }
            else {
                System.exit(1);
            }

        }

    }
}

