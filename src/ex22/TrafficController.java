package ex22;

public class TrafficController {
    private volatile boolean flag = false;
    private Object flagUpdate = new Object();

    public void enterLeft() {
        Crossing();
    }

    public void enterRight() {
        Crossing();
    }

    public void leaveLeft() {
        setFlag(false);
    }

    public void leaveRight() {
        setFlag(false);
    }

    public synchronized void Crossing(){
        while (flag) {
            flag = getFlag();
        }
        setFlag(true);
    }

    public boolean getFlag(){
        synchronized (flagUpdate) {
            return this.flag;
        }
    }

    private void setFlag(boolean flag) {
        synchronized (flagUpdate) {
            this.flag = flag;
        }
    }
}