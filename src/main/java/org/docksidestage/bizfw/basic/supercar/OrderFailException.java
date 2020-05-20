package org.docksidestage.bizfw.basic.supercar;

/**
 * クライアントの要求に基づく車の発注に失敗した場合に発生する例外
 * @author hayato.sasaki
 */
public class OrderFailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderFailException(String msg, Throwable error) {
        super(msg, error);
    }
}
