import java.util.ArrayList;

public class ProcessControlBlock {
    
    private final int pid;
    private ProcessState state;
    // the following two ArrayLists should record when the process starts/stops
    // for statistical purposes
    private ArrayList<Integer> startTimes; // when the process starts running
    private ArrayList<Integer> stopTimes;  // when the process stops running
    
    private static int pidTotal= 0;
    
    public ProcessControlBlock() {
        this.state = ProcessState.NEW;
        this.startTimes = new ArrayList<Integer>();
        this.stopTimes = new ArrayList<Integer>();
        /* TODO: you need to add some code here
         * Hint: every process should get a unique PID */
        this.pid = pidTotal++; // change this line

    }

    public ProcessState getState() {
        return this.state;
    }
    
    public void setState(ProcessState state, int currentClockTime) {
        /* TODO: you need to add some code here
         * Hint: update this.state, but also include currentClockTime
         * in startTimes/stopTimes */
        if (state == ProcessState.RUNNING) {
            startTimes.add(currentClockTime);
        }
        //Κάνουμε δεύτερο έλεγχο γιατί η διεργασία μπορεί να ήταν στην κατάσταση NEW
        else if (this.state == ProcessState.RUNNING && (state == ProcessState.READY || state == ProcessState.TERMINATED)) {
            stopTimes.add(currentClockTime);
        }
        this.state = state;
    }
    
    public int getPid() { 
        return this.pid;
    }
    
    public ArrayList<Integer> getStartTimes() {
        return startTimes;
    }
    
    public ArrayList<Integer> getStopTimes() {
        return stopTimes;
    }
    
}
