import java.util.ArrayList;

/**
 *  Η συγκεκριμένη κλάση υλοποιεί τον αλγόριθμος "NextFit" .
 *  Περιέχει βοηθητικές  μεθόδους που τον υλοποιούν.
 *  Περισσότερες λεπτομέρειες παρακάτω.
 */
public class NextFit extends MemoryAllocationAlgorithm {

    /**
     * Κατασκευαστής της κλάσης . Χρησιμοποιεί τον κατασκευαστή
     * της abstract  κλάσης  " MemoryAllocationAlgorithm ".
     * Επίσης , αρχικοποιείται η μεταβλητή "position" στο μηδέν.
     * @param availableBlockSizes Ένας πίνακας ακεραίων που περιέχει τα
     *        μεγέθη των block μνήμης που μπορούν να χρησιμοποιηθούν.
     */
    public NextFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
        position = 0;
    }

    /**
     * Αυτή η μεταβλητή χρησιμοποιείται για να αποθηκεύσει το που σταμάτησε την αναζήτηση
     * ο αλγόριθμος στην προηγούμενη εκτέλεση.
     */

    private  int position;


    /**
     * Μέθοδος, η οποία βρίσκει τον block στο οποίο πρέπει να αντιστοιχηθεί η
     * διεργασία που αναλύουμε χρησιμοποιώντας τον αλγόριθμος "NextFit" .
     * Συγκεκριμένα , ξεκινάμε με τον πίνακα "blocks" , στον  οποίο αντιγράφουμε τα στοιχεία
     * του πίνακαα availableBlockSizes. Στην συνέχεια, ξεκινάμε μια προσπέλαση  του πίνακα συγκρίνωντας
     * τα περιεχόμενά του με το MemoryRequirement της διεργασίας .
     * Αν βρεθεί στοιχείο που το ικανοποιεί , επιστρέφουμε το μέγεθός του. Αν όχι , επιστρέφουμε -1.
     * Το στοιχείο αυτό είναι το πρώτο, το οποίο θα καλύπτει το MemoryRequirement της διεργασίας , αγνοώντας
     * το γεγονός, ότι παρακάτω μπορεί να υπάρχουν blocks , που θα ταίριαζαν "καλύτερα".
     * @param p Η διεργασία
     * @param pos  Η θέση απ την οποία θα αρχίσει να ψάχνει τα στοιχεία.
     * @return Επιστρέφουμε το μέγεθος του block μνήμης που ικανοποιεί τις προϋποθέσεις.
     */
    private int findFit(Process p, int pos){
        int[] blocks = availableBlockSizes;
        for(int i = position; i< blocks.length-1; i++){
            if(blocks[i] >= p.getMemoryRequirements()){
                position = i ;
                return blocks[i];
            }
        }
        return -1;
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
     * Παρακάτω πάει στο currentlyUsedMemorySlots και βλέπει ποιο MemorySlot έχει το block  μνήμης βρέθηκε
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
        /* TODO: you need to add some code here
         * Hint: this should return the memory address where the process was
         * loaded into if the process fits. In case the process doesn't fit, it
         * should return -1. */
        int sizeOfBlock = findFit(p,position);
        if(sizeOfBlock == -1){
            System.out.println("Error");
            return -1;
        }

        for(MemorySlot m: currentlyUsedMemorySlots){
            if((m.getBlockEnd() - m.getBlockStart()) == sizeOfBlock){
                fit = true;
                address = m.getStart();
            }
        }

        if(fit){
            int index = findIndex(availableBlockSizes,sizeOfBlock);
            if (availableBlockSizes.length - 1 - index >= 0)
                System.arraycopy(availableBlockSizes, index + 1, availableBlockSizes, index, availableBlockSizes.length - 1 - index);
        }

        return address;
    }

}
