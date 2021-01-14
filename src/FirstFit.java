import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm {
    
    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    private int findFit(Process p){
        int[] blocks = availableBlockSizes;
        for(int i = 0; i< blocks.length-1; i++){
            if(blocks[i] >= p.getMemoryRequirements()){
                return blocks[i];
            }
        }
        return -1;
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
        /* TODO: you need to add some code here
         * Hint: this should return the memory address where the process was
         * loaded into if the process fits. In case the process doesn't fit, it
         * should return -1. */
        int sizeOfBlock = findFit(p);
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
