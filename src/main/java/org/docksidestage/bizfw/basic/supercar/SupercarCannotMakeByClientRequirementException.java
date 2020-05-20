package org.docksidestage.bizfw.basic.supercar;

/**
 * 顧客の要求に基づいてsupercarを製造できない場合に発生する例外
 * @author hayato.sasaki
 */
public class SupercarCannotMakeByClientRequirementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SupercarCannotMakeByClientRequirementException(String msg, Throwable error) {
        super(msg, error);
    }
}
