import java.util.ArrayList;
import java.util.Arrays;

public class BestFit extends MemoryAllocationAlgorithm {
    
    public BestFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

   private int findFit(Process p, int[] blocks){
       int megethos = 0;
       Arrays.sort(blocks);
       for(int i=0 ; i<blocks.length-1;i++){
           if(blocks[i] >= p.getMemoryRequirements()){
                megethos = blocks[i];
           }
           else{
               return -1;
           }
       }
       return megethos;
   }

   private int findIndex(int[] array, int megethos){

        for(int i=0;i<array.length-1;i++){
            if(array[i] == megethos){
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
        int megethos = findFit(p,availableBlockSizes);
        if(megethos == -1){
            return address ;
        }

        for(MemorySlot m : currentlyUsedMemorySlots){
            if((m.getBlockEnd()-m.getBlockStart()) == megethos){
                address = m.getStart();
                fit = true;
            }
        }
        if(fit == true){
            int index = findIndex(availableBlockSizes,megethos);
            for(int i=index;i<availableBlockSizes.length-1;i++){
                availableBlockSizes[i] = availableBlockSizes[i+1];
            }
        }

        return address;
    }

}
