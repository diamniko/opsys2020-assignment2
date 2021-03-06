import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Η συγκεκριμένη κλάση υλοποιεί τον αλγόριθμος "WorstFit" .
 *  Περιέχει βοηθητικές  μεθόδους που τον υλοποιούν.
 *  Περισσότερες λεπτομέρειες παρακάτω.
 */
public class WorstFit extends MemoryAllocationAlgorithm {

    /**
     * Κατασκευαστής της κλάσης . Χρησιμοποιεί τον κατασκευαστή
     * της abstract  κλάσης  " MemoryAllocationAlgorithm "
     *
     * @param availableBlockSizes Ένας πίνακας ακεραίων που περιέχει τα
     *        μεγέθη των block μνήμης που μπορούν να χρησιμοποιηθούν.
     */
    public WorstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    /**
     * Μέθοδος, η οποία βρίσκει τον block στο οποίο πρέπει να αντιστοιχηθεί η
     * διεργασία που αναλύουμε χρησιμοποιώντας τον αλγόριθμος "WorstFit" .
     * Συγκεκριμένα , ξεκινάμε με τον πίνακα "blocks" , στον  οποίο αλλάζουμε τα στοιχεία του,
     * για να είναι απ' το μικρότερο στο μεγαλύτερο με την μέθοδο sort. Στην συνέχεια, ξεκινάμε
     * μια προσπέλαση  του πίνακα απ' το τέλος προς την αρχή ( βλέπωντας δηλαδή απ το μεγαλύτερο στοιχείο
     * προς το μικρότερο ) συγκρίνωντας τα περιεχόμενά του με το MemoryRequirements
     * της διεργασίας . Αν βρεθεί στοιχείο που το ικανοποιεί , επιστρέφουμε το μέγεθός του
     * . Αν όχι , επιστρέφουμε -1.
     * Στον συγκεκριμένο αλγόριθμο , τα μεγαλύτερα blocks καλύπτονται πρώτα , γεγονός, το οποίο την κάνει
     * λιγότερο αποδοτική, διότι μπορεί να υπάρξουν διεργασίες που μένουν ανεκπλήροτες , ενώ με καλύτερη διαχεί-
     * ριση μπορεί να εξυπηρετούνταν.
     *
     * @param p Η διεργασία
     * @param blocks ο πίνακας "blocks" για τα δεδομένα .  Στις κλήσεις θα περνάμε τον πίνακα "availableBlockSizes"
     * @return Επιστρέφουμε το μέγεθος του block μνήμης που ικανοποιεί τις προϋποθέσεις.
     */
    private int findFit(Process p, int[] blocks){
        int sizeOfBlock = 0;
        Arrays.sort(blocks);
        for(int i=blocks.length-1; i>0;i--){
            if(blocks[i] >= p.getMemoryRequirements()){
                sizeOfBlock = blocks[i];
            }
            else{
                return -1;
            }
        }
        return sizeOfBlock;
    }

    /**
     * Μέθοδος , η οποία βρίσκει ποιο στοιχείο του "availableBlockSizes" έχει το
     * μέγεθος , που μας ικανοποιεί .
     * Προσπελαύνουμε όλον τον πίνακα και όταν βρούμε ότι τα στοιχεία ταυτίζονται
     * επιστρέφουμε την θέση που υποδεικνύεται απ'  την μεταβλητή i.
     *
     * @param array Ο πίνακας "availableBlockSizes"
     * @param sizeOfBlock Το μέγεθος που μας ικανοποιεί.
     * @return Επιστρέφεται το index της θέσης του στοιχείου .
     */
    private int findIndex(int[] array, int sizeOfBlock){

        for(int i=0;i<array.length-1;i++){
            if(array[i] == sizeOfBlock){
                return i;
            }
        }
        return -1;
    }

    /**
     * Μέθοδος που υλοποιεί την διαδικασία της αντιστοίχησης .
     * Πρωτίστως βρίσκουμε εάν υπάρχει block  μνήμης που ικανοποιεί τις απαιτήσεις της διεργασίας
     * χρησιμοποιώντας την μέθοδο findFit που αναπτύχθηκε παραπάνω.
     * Αν η μέθοδος βρει το επιθυμητό block  μνήμης αποθηκεύουμε το μέγεθός του στην μεταβλητή sizeOfBlock . Αν όχι,
     * η μέθοδος επιστρέφει -1  , σταματάει την εκτέλεση και βγάζει μήνυμα.
     * Παρακάτω, πάει στο currentlyUsedMemorySlots και βλέπει ποιο MemorySlot έχει το block  μνήμης βρέθηκε
     * πάνω κάνωντας την αφαίρεση getBlockEnd() - getBlockStart() και συγκρίνοντας το με την sizeOfBlock .
     * Αν βρεθεί το ζητούμενο , τότε η μεταβλητή address παίρνει την τιμή getStart() του MemorySlot , η οποία είναι
     * η τιμή διεύθυνσης απ' όπου και ξεκινάει και αλλάζουμε την τιμή της μεταβλητής fit σε true.
     * Τέλος , αφαιρούμε το συγκεκριμένο block απ το availableBlockSizes.
     * @param p Η διεργασία που αναλύουμε
     * @param currentlyUsedMemorySlots ArrayList που περιέχει τα MemorySlots που χρησιμοποιούνται.
     * @return Η διέυθυνση μνήμης που περιέχεται στην μεταβλητή address εφόσον η μέθοδος ήταν επιτυχής , αλλίως
     *          -1 για να δείξουμε, ότι δεν πέτυχε.
     */
    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;
        int sizeOfBlock = findFit(p,availableBlockSizes);

        if(sizeOfBlock == -1){
            System.out.println("Error");
            return address;
        }

        for(MemorySlot m: currentlyUsedMemorySlots){
            if((m.getBlockEnd() - m.getBlockStart()) == sizeOfBlock){
                address = m.getStart();
                fit = true;
            }
        }

        if(fit){
            int index = findIndex(availableBlockSizes,sizeOfBlock);
            if (availableBlockSizes.length - 1 - index >= 0)
                System.arraycopy(availableBlockSizes, index + 1, availableBlockSizes, index, availableBlockSizes.length - 1 - index);
        }
        /* TODO: you need to add some code here
         * Hint: this should return the memory address where the process was
         * loaded into if the process fits. In case the process doesn't fit, it
         * should return -1. */

        return address;
    }

}
