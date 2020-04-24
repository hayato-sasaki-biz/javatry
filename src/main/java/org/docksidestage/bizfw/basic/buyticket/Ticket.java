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
 * @author hayato.sasaki
 */
public interface Ticket {

    // done sasaki [いいね] タグコメント良いですね〜。必須ではないですけど、メソッドたちもカテゴリがあるので、しっかり意識するのは大切 by jflute (2020/04/23)
    // ぼくはタグコメントよく使います。
    // https://github.com/lastaflute/lastaflute-example-harbor/blob/master/src/main/java/org/docksidestage/mylasta/direction/HarborFwAssistantDirector.java#L57
    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // done sasaki interfaceは、publicのメソッドしか定義できなくて、publicを省略できます。みんなよく省略するので削除しちゃいましょう by jflute (2020/04/23)
    void doInPark();
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    int getDisplayPrice();
    int getDisplayDay();
    // done sasaki AlreadyIn というのが何を示すものなのか？要件が曖昧ですが...MultipleDaysTicketを見ると、完全消化したらtrueという実装なので... by jflute (2020/04/23)
    // そういったことをインターフェースのメソッドのJavaDocコメントで書いておくと良いです。
    // わかりきってるメソッドはコメントなくてもって感じですが、解釈が分かれそうなものは、しっかりコメント書いておきましょう。
    // (まあ、メソッド名を変えるというのも手ですが...)

    /**
     * チケットが消化済みかどうか
     * @return チケットを完全消化したらtrue
     */
    boolean isAlreadyIn();
}
