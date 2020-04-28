package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for cow(牛).
 * @author hayato.sasaki
 */
public class Cow extends Animal {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Cow() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        return "moo";
    }
}
