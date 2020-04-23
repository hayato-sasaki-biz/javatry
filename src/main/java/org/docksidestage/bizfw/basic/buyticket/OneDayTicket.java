package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author hayato.sasaki
 */
public class OneDayTicket implements Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int displayDay = 1;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public OneDayTicket(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getDisplayDay() {
        return displayDay;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
