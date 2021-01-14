
public class SJF extends Scheduler {

    public SJF() {
        /* TODO: you _may_ need to add some code here */
        while (processes.size() > 0) {
            Process currentProcess = getNextProcess();
            //Μετά την εκτέλεση μιας διεργασίας την σταματάει και την αφαιρεί από τη λίστα
            currentProcess.waitInBackground();
            removeProcess(currentProcess);
        }
    }

    public void addProcess(Process p) {
        /* TODO: you need to add some code here */
        processes.add(p);   //Απλά προσθέτει τη διεργασία p στη λίστα
    }
    
    public Process getNextProcess() {
        /* TODO: you need to add some code here
         * and change the return value */
        //Αναζητούμε τη διεργασία με το μικρότερο χρόνο άφιξης
        int minArrivalTime = processes.get(0).getArrivalTime();
        int minIndex = 0;
        for (int i=1; i<processes.size(); i++) {
            if (processes.get(i).getArrivalTime() < minArrivalTime) {
                minArrivalTime = processes.get(i).getArrivalTime();
                minIndex = i;
            }
        }
        Process nextProcess = processes.get(minIndex);
        nextProcess.run();
        return nextProcess;
    }
}
