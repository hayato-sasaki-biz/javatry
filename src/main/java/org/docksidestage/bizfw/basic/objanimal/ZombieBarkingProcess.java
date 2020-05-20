package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author hayato.sasaki
 */
public class ZombieBarkingProcess extends BarkingProcess {

    @Override
    protected void breatheIn(Animal zombie) {
        super.breatheIn(zombie);
        ((Zombie) zombie).zombieDiary.countBreatheIn();
    }
}
