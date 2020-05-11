package org.docksidestage.bizfw.basic.objanimal.swimmer;

import org.docksidestage.bizfw.basic.objanimal.runner.Bicycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ship implements Swimmer {

    private static final Logger logger = LoggerFactory.getLogger(Ship.class);

    @Override
    public void swim() {
        // dummy implementation
        logger.debug("My ship leaves port...");
    }
}
