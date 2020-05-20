package org.docksidestage.bizfw.basic.supercar;

/**
 * 特別なネジが作成できなかった場合に発生する例外
 * @author hayato.sasaki
 */
public class SpecialScrewCannotMakeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SpecialScrewCannotMakeException(String msg, Throwable error) {
        super(msg, error);
    }
}
