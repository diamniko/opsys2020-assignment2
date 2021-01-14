
public class Process {
    private ProcessControlBlock pcb;
    private int arrivalTime;
    private int burstTime;
    private int memoryRequirements;
    
    public Process(int arrivalTime, int burstTime, int memoryRequirements) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.memoryRequirements = memoryRequirements;
        this.pcb = new ProcessControlBlock();
    }
    
    public ProcessControlBlock getPCB() {
        return this.pcb;
    }
   
    public void run() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process starts running */
        pcb.setState(ProcessState.RUNNING, CPU.clock);  //Αλλαγή κατάστασης σε RUNNING
    }
    
    public void waitInBackground() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process stops running */
        //Αφαιρούμε τον χρόνο άφιξης γιατί από εκείνο το σημείο ξεκινάει να μετράει ο χρόνος αναμονής
        double runningTime = CPU.clock - getWaitingTime() - arrivalTime;
        if (runningTime == burstTime) {
            pcb.setState(ProcessState.TERMINATED, CPU.clock);
        }
        else {
            pcb.setState(ProcessState.READY, CPU.clock);
        }
    }

    public double getWaitingTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        double waitingTime = getResponseTime();
        //Προσθέτουμε τα διαστήματα αναμονής από το πρώτο stop μέχρι το τελευταίο start
        for (int i=1; i<pcb.getStartTimes().size(); i++) {
            waitingTime += pcb.getStartTimes().get(i) - pcb.getStopTimes().get(i-1);
        }
        //Αν η διεργασία συνεχίζει να βρίσκεται σε αναμονή τότε ο χρόνος αναμονής συνεχίζει να αυξάνεται
        if (pcb.getState() == ProcessState.READY) {
            waitingTime += CPU.clock - pcb.getStopTimes().get(pcb.getStopTimes().size()-1);
        }
        return waitingTime;
    }
    
    public double getResponseTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        return pcb.getStartTimes().get(0) - arrivalTime;    //Η διαφορά μεταξύ του χρόνου εκκίνισης και του χρόνου άφιξης
    }
    
    public double getTurnAroundTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        if (pcb.getState() == ProcessState.TERMINATED) {    //Επιστρέφει τιμή μόνο αν η διεργασία έχει ολοκληρωθεί
            return pcb.getStopTimes().get(pcb.getStopTimes().size()-1) - arrivalTime;
        }
        return 0;
    }
    protected int getMemoryRequirements(){
        return memoryRequirements;
    }
}
