package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author hayato.sasaki
 */
public class MultipleDaysTicket implements Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int displayDay;
    private int countIn;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public MultipleDaysTicket(int displayPrice, int day) {
        this.displayPrice = displayPrice;
        this.displayDay = day;
        this.countIn = day;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (countIn <= 0) {
            throw new IllegalStateException(
                String.format("Alreday in park by this ticket %d times: displayedPrice=%d" , displayDay, displayPrice)
            );
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
