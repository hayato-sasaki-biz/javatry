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
import java.util.Map;
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
    // TODO done sasaki まだわからないかもですが、インターフェースで受けましょう。ここだとMap型 by jflute (2020/04/23)
    // Mapを使うがわからしたら、Hashで作られたMapかどうかは気にしないので、純粋にMapというオブジェクトで扱いたいです。
    // (Step6のポリモーフィズムのところに関連、いまあんまりわからなくてもOK)
    private static final Map<Integer, Integer> PRICES = new HashMap<Integer, Integer>() {
        {
            put(1, 7400);
            put(2, 13200);
            put(4, 22400);
        }
    };

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
    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, 1);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, 2);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, 4);
    }

    // TODO done sasaki 再利用するprivateメソッドは、利用する側のメソッドの下に宣言する習慣があるので移動してみてください(現場にも寄りますが) by jflute (2020/04/23)
    // TODO done sasaki Slackのtipsスレッドで書いたように、privateの実処理メソッドは doBuy...() にした方が区別がつきやすいです by jflute (2020/04/23)
    // buyOneDayPassportやbuyTwoDayPassportを一般化
    private TicketBuyResult doBuyPassport(int handedMoney, int day) {
        // TODO sasaki まとまった処理ごとに概要コメントがあるのいいですね。複数の処理が一つになって一つの業務をおこなっているわけですからね by jflute (2020/04/23)
        // 売り切れチェック
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        // 支払い金額のチェック
        final int price = deriveTicketPrice(day);
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

    // TODO done sasaki getは曖昧な言葉(最終手段or本当に取るだけ)なので、できるだけ何か意味のある単語を by jflute (2020/04/23)
    // 例えば、dayからPriceを導いているので... deriveTicketPrice() とか。探していることを強調したければ findTicketPrice() とか。
    // 動詞の語彙力も大切です。まあ、プログラミングでよく使われる動詞ってのもあるので、たくさんソースコード読むことですね。
    private int deriveTicketPrice(int day) {
        // チケットの価格の設定とDayに関する例外処理
        Integer price = PRICES.get(day);
        if (price == null) {
            // TODO done sasaki SetはGeneric型がひつようなので、Set<Integer> などで受け取ってください by jflute (2020/04/23)
            Set<Integer> daySet = PRICES.keySet();
            // TODO done sasaki toString()しなくても、文字列と連結するだけで自然とtoString()されます by jflute (2020/04/23)
            //  e.g. ...following: " + daySet);
            throw new TicketInvalidDayException("Day of ticket should be either of the following: " + daySet);
        }
        return (int) price;
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

    // TODO sasaki [いいね] 日本語のコメントGood. ちゃんと真似て独自例外を作ってるのもGood by jflute (2020/04/23)
    // doBuyPassportでdayの値が不正だった場合の例外
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
