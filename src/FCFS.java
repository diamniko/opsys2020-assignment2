
public class FCFS extends Scheduler {

    public FCFS() {
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
        //Παίρνει την πρώτη διεργασία από τη λίστα και την εκτελεί
        Process nextProcess = processes.get(0);
        nextProcess.run();
        return nextProcess;
    }
}
