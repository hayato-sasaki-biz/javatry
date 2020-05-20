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
package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // DONE getBarkingProcess メソッドを作るのがおすすめです。 by subaru (2020/05/20)
    // 今回コンストラクタで Animal クラスと異なるのは barkingProcess だけのはずですが、hitPoint の処理も同じものを指定し直してまっています。
    // 今回はコンストラクタの処理が少ないのでまだ良いですが、処理が多くなるとバグを生む原因になるので次のようにするのがおすすめです。
    // public Animal() {
    //   hitPoint = getInitialHitPoint();
    //   barkingProcess = getBarkingProcess();
    // }
    // そして Zombie の実装時には getBarkingProcess メソッドをオーバーライドしてあげるだけで、実装としては完了します。
    public Zombie() {
    }

    @Override
    protected BarkingProcess getBarkingProcess() {
        return new ZombieBarkingProcess();
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======

    @Override
    protected String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }
}
