package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hayato.sasaki
 */
public class BarkingProcess {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess() {
    }

    // ===================================================================================
    //                                                                      Bark processes
    //                                                                         ===========
    public BarkedSound letAnimalBark(Animal animal) {
        breatheIn(animal);
        prepareAbdominalMuscle(animal);
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = doBark(animal, barkWord);
        return barkedSound;
    }

    protected void prepareAbdominalMuscle(Animal animal) {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        animal.downHitPoint();
    }

    protected void breatheIn(Animal animal) {
        logger.debug("...Breathing in"); // dummy implementation
        animal.downHitPoint();
    }

    protected BarkedSound doBark(Animal animal, String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }


}
