package org.docksidestage.bizfw.basic.supercar;

/**
 * ハンドルが作成できなかった場合に発生する例外
 * @author hayato.sasaki
 */
public class SteeringWheelCannotMakeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SteeringWheelCannotMakeException(String msg, Throwable error) {
        super(msg, error);
    }
}
