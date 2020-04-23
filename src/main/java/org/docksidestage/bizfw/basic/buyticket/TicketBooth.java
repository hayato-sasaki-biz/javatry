/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author hayato.sasaki
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200; // when 2020/04/22

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========

    // buyOneDayPassportとbuyTwoDayPassportを一般化
    private TicketBuyResult buyPassport(int handedMoney, int day) {
        // チケットの価格の設定とDayに関する例外処理
        int price;
        switch (day) {
        case 1:
            price = ONE_DAY_PRICE;
            break;
        case 2:
            price = TWO_DAY_PRICE;
            break;
        default:
            // 購入チケット数numTicketに関する例外処理
            throw new TicketInvalidDayException("Day of ticket should be 1 or 2");
        }

        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }

        Ticket ticket = new Ticket(price, day);
        int change = handedMoney - price;
        return new TicketBuyResult(ticket, change);
    }

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        return buyPassport(handedMoney, 1);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return buyPassport(handedMoney, 2);
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // buyPassportでdayの値が不正だった場合の例外
    public static class TicketInvalidDayException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketInvalidDayException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
