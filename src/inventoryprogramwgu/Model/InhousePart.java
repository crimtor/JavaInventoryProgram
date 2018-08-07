/**
 * 
 * Inventory Control Program
 * Completed for WGU C482
 * Software 1
 * @author Shawn T Fox
 * Student ID: #000545644
 * 
 */
package inventoryprogramwgu.Model;

import inventoryprogramwgu.Model.Part;

public class InhousePart extends Part {
    int machineID;
    
    public InhousePart(){
    partID = Count.incrementAndGet();
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
}
