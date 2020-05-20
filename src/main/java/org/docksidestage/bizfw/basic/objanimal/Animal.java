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

import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

/**
 * The object for animal(動物).
 * @author jflute
 */
public abstract class Animal implements Loudable {

    // DONE sasaki 細かいけど使ってないなら消してしまいましょう。 by subaru (2020/05/20)

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint;
    protected BarkingProcess barkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
        barkingProcess = new BarkingProcess();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        // NOTE bark()はAnimalのhitPointを変化させる
        // ここでは、Animalの他のプロセスもhitPointを変化させる可能性があると仮定し、
        // BarkingProcessにはhitPointのフィールドをもたせていない(downHitPointはAnimalが持つべき)
        return barkingProcess.letAnimalBark(this);
    }

    protected abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
