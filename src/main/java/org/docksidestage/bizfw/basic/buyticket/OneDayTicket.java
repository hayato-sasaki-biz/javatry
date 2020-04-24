package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author hayato.sasaki
 */
public class OneDayTicket implements Ticket {

    // ===================================================================================
    //                                                                           Definition
    //                                                                           =========
    // done sasaki [tips]static finalの定数っぽいものは、DISPLAY_DAY というように大文字にスネークケースにする慣習があるのでRenameしてみよう by jflute (2020/04/24)
    private static final int DISPLAY_DAY = 1;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    // done sasaki この場合は、インスタンスに依存せず完全なる定数なので、static final の定数にしてしまいましょう by jflute (2020/04/23)
    // (static finalのものは、Attribute じゃなくて、Definition というタグコメントをよく使います)
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
        return DISPLAY_DAY;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
