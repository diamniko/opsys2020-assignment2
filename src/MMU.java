import java.util.ArrayList;

public class MMU {

    private final int[] availableBlockSizes;
    private MemoryAllocationAlgorithm algorithm;
    private ArrayList<MemorySlot> currentlyUsedMemorySlots;

    public MMU(int[] availableBlockSizes, MemoryAllocationAlgorithm algorithm) {
        this.availableBlockSizes = availableBlockSizes;
        this.algorithm = algorithm;
        this.currentlyUsedMemorySlots = new ArrayList<MemorySlot>();
    }

    /**
     * Εάν η διαδικασία αντιστοίχισης της διεργασίας με κάποιο block μνήμης ήταν επιτυχής η τιμή
     * του fit πρέπει να είναι true. Αν όχι, τότε πρέπει να είναι false.
     * Με την προσέγγιση που υιοθετήθηκε στις κλάσεις που κάνουν extend την MemoryAllocationAlgorithm
     * η μέθοδος  fitProcess επιστρέφει -1 αν έληξε ανεπιτυχώς και κάποια τιμή , που είναι η διέυθυνση του
     *  MemorySlot , όπου έγινε η αντιστοίχηση . Βλέπουμε οπότε εάν η τιμή που επιστρέφει είναι διάφορη του
     *  -1 , αλλάζουμε την τιμή του fit σε true. Αλλιώς , αυτή παραμένει false
     * @param p Η διεργασία .
     * @return True , αν η αντιστοίχιση έγινε με επιτυχία  . False , αν ήταν ανεπιτυχής.
     */
    public boolean loadProcessIntoRAM(Process p) {
        boolean fit = false;
        if(algorithm.fitProcess(p,currentlyUsedMemorySlots) != -1){
            fit = true ;
        }
        /* TODO: you need to add some code here
         * Hint: this should return true if the process was able to fit into memory
         * and false if not */
        
        return fit;
    }
}
