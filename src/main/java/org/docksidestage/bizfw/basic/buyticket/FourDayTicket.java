package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author hayato.sasaki
 */
public class FourDayTicket implements Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int displayDay = 4;
    private int countIn = 4;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public FourDayTicket(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (countIn <= 0) {
            throw new IllegalStateException("Alreday in park by this ticket four times: displayedPrice=" + displayPrice);
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
