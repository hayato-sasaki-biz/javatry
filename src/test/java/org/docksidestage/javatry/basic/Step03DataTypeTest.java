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
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author hayato.sasaki
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);      // piari => 2001/09/05
        land = piari.getYear();         // land => 2001
        bonvo = bonvo.plusMonths(1);    // bonvo => 2001/10/04 12:34:56
        land = bonvo.getMonthValue();   // land => 10
        land--;                         // land => 9
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));       // addedDecimal => 18.4
            sea = String.valueOf(addedDecimal); // sea => 18.4
        }
        log(sea); // your answer? => 18.4
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 2147483647; // max
        long bonvo = 9223372036854775807L; // max
        float dstore = 2147483647.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';    // dohotel => true
        if (dohotel && dstore >= piari) {   // ()の中身: true & true => true
            bonvo = sea;            // sea:byte, bonvo:long,        bonvo => 127L
            land = (short) bonvo;   // bonvo:long, land: short,     land => 127
            bonvo = piari;          // piari:int, bonvo:long,       bonvo => 2147483647
            sea = (byte) land;      // land: short, sea: byte       sea => 127
            if (amba == 2.3D) {     // ()の中身: true
                sea = (byte) amba;  // sea => 2
            }
        }
        if (dstore > piari) {       // ()の中身: false, 理由: dstoreとpiariを比較するとequal
            sea = 0;
        }
        log(sea); // your answer? => 2
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        // ポイント: コンストラクタで設定したインスタンス変数をgetStageNameメソッドで呼び出している
        log(sea); // your answer? => hanger
    }

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
