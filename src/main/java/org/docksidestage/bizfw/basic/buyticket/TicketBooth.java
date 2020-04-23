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

import java.util.HashMap;
import java.util.Set;

/**
 * @author jflute
 * @author hayato.sasaki
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final HashMap<Integer, Integer> PRICES = new HashMap<Integer, Integer>() {{
        put(1, 7400);
        put(2, 13200);
        put(4, 22400);
    }};

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

    // buyOneDayPassportやbuyTwoDayPassportを一般化
    private TicketBuyResult buyPassport(int handedMoney, int day) {
        // 売り切れチェック
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        // 支払い金額のチェック
        final int price = getTicketPrice(day);
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }

        // 購入処理
        --quantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }

        // チケットとお釣りの準備
        int change = handedMoney - price;
        Ticket ticket = day == 1 ? new OneDayTicket(price) : new MultipleDaysTicket(price, day);
        return new TicketBuyResult(ticket, change);
    }

    private int getTicketPrice(int day) {
        // チケットの価格の設定とDayに関する例外処理
        Integer price = PRICES.get(day);
        if (price == null) {
            Set daySet = PRICES.keySet();
            throw new TicketInvalidDayException("Day of ticket should be either of the following: " + daySet.toString());
        }
        return (int) price;
    }

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        return buyPassport(handedMoney, 1);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return buyPassport(handedMoney, 2);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        return buyPassport(handedMoney, 4);
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
