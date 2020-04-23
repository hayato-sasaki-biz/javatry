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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author hayato.sasaki
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        // ポイント: Step04MethodTestのメソッドsupplySomething()が呼ばれている
        log(sea); // your answer? => over
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic"); // sea => mysmys
        consumeSomething(supplySomething()); // 上の行のseaは変化しない
        runnableSomething();    // logをするだけでseaは変化なし
        log(sea); // your answer? => mysmys
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);   // mutable.stage => mystic
        if (!land) {
            // 上記のhelloMutableではlandに影響を与えないので!landはtrue
            sea = sea + mutable.getStageName().length();    // mutable.stage.length() => 6
        }
        log(sea); // your answer? => 910
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;      // sea => 0
        offAnnualPassport(hasAnnualPassport);   // インスタンス変数のhasAnnualPassportは変化なし
        for (int i = 0; i < 100; i++) {
            goToPark();
        }   // inParkCount => 100
        ++sea;  // sea => 1
        sea = inParkCount;  // sea => 100
        log(sea); // your answer? => 100
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAtoB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCtoB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o addPrefix(): has two arguments as String, returns combined first argument with ":" with second argument 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAtoB(): 一つのString引数、引数を "A" を "B" に置き換えらたStringを戻す 
     * o replaceCtoB(): 一つのString引数、引数を "C" を "B" に置き換えらたStringを戻す 
     * o addPrefix(): 二つのString引数、第一引数と ":" と第二引数を連結したものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // comment out after making these methods
        String replaced = replaceCtoB(replaceAtoB("ABC"));
        String sea = addPrefix("broadway", replaced);
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    // write methods here
    private boolean availableLogging = true;

    private String replaceAtoB(String target) {
        // DONE この処理であれば replaced という変数に切り出さなくても問題なさそうです。 by subaru (2020/04/22)
        // DONE target が null だったらどうなるか、そこも想定して実装してみましょう！ by subaru (2020/04/22)
        // ↑ replaceBtoC、addPrefix メソッドについても同様に修正してください。
        // DONE [comment] Good です。 by subaru (2020/04/22)
        // あとは少し好みですが、三項演算子を使うとよりシンプルにかけます。
        // NOTE 三項演算子を使った書き方に変更しました by sasaki (2020/04/23)
        return target == null ? target : target.replace("A", "B");
    }

    private String replaceCtoB(String target) {
        return target == null ? target : target.replace("C", "B");
    }

    private String addPrefix(String prefix, String text) {
        // TODO [comment] この辺に疑問を持つのはすばらしいです！ by subaru (2020/04/22)
        // null の時に何かを return するべきか例外を投げるべきか、考えることはめっちゃ大事です。
        // 結論としては、そのメソッドの仕様によります。
        // 基本的にこのメソッドの使用の仕方として、
        // - null を代入することが想定されている -> （空文字など何かを）return する
        // - null が引数で渡ってくることが想定されていない（異常系） -> 例外をスローする
        // のように考えると良いと思います。
        // 今回に関しては特に指定はないのでどれが正解ということはありません。
        // 佐々木さんが考えるあるべき論で実装して大丈夫かと思います。

        // NOTE: prefixはnullではないと仮定 (この場合は例外を投げたほうが良い？)
        return text == null ? prefix + ":" : prefix + ":" + text;
    }

    private boolean isAvailableLogging() {
        return availableLogging;
    }

    private void showSea(String sea) {
        log(sea);
    }

}
