
public class RoundRobin extends Scheduler {

    private int quantum;

    private int index;  //Δείκτης για τη λίστα διεργασιών
    
    public RoundRobin() {
        this.quantum = 1; // default quantum
        /* TODO: you _may_ need to add some code here */
        index = 0;
        while (processes.size() > 0) {
            Process currentProcess = getNextProcess();
            //Ελέγχομε αν η διεργασία θα χρειαστεί λιγότερο χρόνο για να εκτελεστεί
            double runningTime = CPU.clock + currentProcess.getBurstTime() - currentProcess.getWaitingTime()
                    - currentProcess.getArrivalTime();
            if (runningTime < quantum) {
                CPU.clock += currentProcess.getBurstTime();
            }
            CPU.clock += quantum;   //Η διεργασία εκτελείται για δεδομένη ενότητα χρόνου
            currentProcess.waitInBackground();
            removeProcess(currentProcess);  //Αν δεν έχει ολοκληρωθεί δεν θα διαγραφεί
        }
    }
    
    public RoundRobin(int quantum) {
        this();
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        /* TODO: you need to add some code here */
        processes.add(p);   //Απλά προσθέτει τη διεργασία p στη λίστα
    }
    
    public Process getNextProcess() {
        /* TODO: you need to add some code here
         * and change the return value */
        Process nextProcess = processes.get(index);
        nextProcess.run();
        //Αν φτάσουμε στο τέλος της λίστας επιστρέφουμε στην αρχή
        if (index >= processes.size()-1) {
            index = 0;
        }
        index++;
        return null;
    }
}
