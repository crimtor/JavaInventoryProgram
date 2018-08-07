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

public class OutsourcedPart extends Part {
    String companyName;
    
    public OutsourcedPart(){
        partID = Count.incrementAndGet();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
