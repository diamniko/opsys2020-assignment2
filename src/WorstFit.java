import java.util.ArrayList;
import java.util.Arrays;

public class WorstFit extends MemoryAllocationAlgorithm {
    
    public WorstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

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

    private int findIndex(int[] array, int sizeOfBlock){

        for(int i=0;i<array.length-1;i++){
            if(array[i] == sizeOfBlock){
                return i;
            }
        }
        return -1;
    }

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
