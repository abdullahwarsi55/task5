public class WaitingMessage implements Runnable {


    private static int counter = 0;
    private static int count = 0;
    @Override
    public void run() {
        while (true) {
            try {
                
                Thread.sleep(5000);
                counter++;
                count++;
                System.out.println("I am waiting for response from server!" +getcount() + "time");
                
               
            } catch (InterruptedException ex) {
                ex.getStackTrace();
            }
        }
    }

    public int getCounter() {
        return counter;
    }

    public void resetCounter() {
        counter = 0;
    }

    private void resetcount() {
        count=0;
    }

    private int getcount() {
        return count;
    }
}