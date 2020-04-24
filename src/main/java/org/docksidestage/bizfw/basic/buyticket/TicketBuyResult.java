package org.docksidestage.bizfw.basic.buyticket;

// TODO sasaki 細かいですが、MultipleDaysTicket では public class 宣言の直下は空行が空いてて、ここは空いてない by jflute (2020/04/24)
// そういった部分もできるだけ一貫性を持ってコードの体裁を整えていこう。こういう思想です。https://twitter.com/jflute/status/1164429226822385664
/**
 * @author hayato.sasaki
 */
public class TicketBuyResult {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private Ticket ticket;
    private int change;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return this.ticket;
    }

    public int getChange() {
        return this.change;
    }
}
