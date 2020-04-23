package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author hayato.sasaki
 */
public class TwoDayTicket implements Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int displayDay = 2;
    private int countIn = 2;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TwoDayTicket(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (countIn <= 0) {
            throw new IllegalStateException("Alreday in park by this ticket two times: displayedPrice=" + displayPrice);
        }
        --countIn;
        if (countIn == 0) {
            alreadyIn = true;
        }
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
