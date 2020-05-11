package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.swimmer.Swimmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hayato.sasaki
 */
public class Dolphin extends Animal implements Swimmer {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Dolphin.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Dolphin() {
    }
    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        return "Cue cue"; // Squeak squeak? in English
    }

    // ===================================================================================
    //                                                                             Swimmer
    //                                                                              ======
    @Override
    public void swim() {
        // dummy implementation
        logger.debug("...Swimming now");
    }
}
