
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
        if (pcb.getStopTimes().isEmpty()) {
           // pcb.setState(ProcessState.RUNNING, (int) getResponseTime());
        }
        else {
            pcb.setState(ProcessState.RUNNING, (int) getWaitingTime());
        }
    }
    
    public void waitInBackground() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process stops running */
        if (getTurnAroundTime() == burstTime) {

        }
        if (pcb.getState() == ProcessState.NEW) {
            pcb.setState(ProcessState.READY, arrivalTime);  //Το arrivalTime δεν θα αποθηκευτεί σε κάποιο ArrayList
        }
        else if (pcb.getState() == ProcessState.RUNNING) {
            if (getTurnAroundTime() - getWaitingTime() == burstTime) {
                pcb.setState(ProcessState.TERMINATED, );
            }
        }
        pcb.setState(ProcessState.READY, burstTime);
    }

    public double getWaitingTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        int sum = 0;
        for (int i=0; i<pcb.getStopTimes().size(); i++) {
            sum += pcb.getStartTimes().get(i+1) - pcb.getStopTimes().get(i);
        }
        return sum;
    }
    
    public double getResponseTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        if (pcb.getStartTimes().isEmpty()) {
            return 0;
        }
        return pcb.getStartTimes().get(0) - arrivalTime;
    }
    
    public double getTurnAroundTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        int sum = 0;
        for (int i=0; i<pcb.getStopTimes().size(); i++) {
            sum += pcb.getStopTimes().get(i) - pcb.getStartTimes().get(i);
        }
        return sum;
    }
}
