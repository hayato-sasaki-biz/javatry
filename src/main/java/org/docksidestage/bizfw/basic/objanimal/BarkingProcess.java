package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO [質問回答1] BarkingProcess を別パッケージに分けるべきか？ by subaru (2020/05/20)
// これは org.docksidestage.bizfw.basic.objanimal に配置されているクラスの数や種類が多くなり、煩雑になってきたかどうかで判断すると良いと思います。
// もしくは煩雑になることが目に見えているなら最初からパッケージを分けてしまっても良いです。
// TODO [質問回答2] Animal.downHitPoint のアクセス修飾子はprotectedからpublicにしても良いのか？ by subaru (2020/05/20)
// これは難しいよね。。
// downHitPoint メソッドが Animal オブジェクトの内部処理であるのならやはり public にするべきではないです。
// 一方で BarkingProcess を別パッケージにしたならば public にせざるを得ない、正直ジレンマです。
// 3つ目の質問も一緒に答えますが、正直仕方がないところもあるので、今のままで良いと思います。
// 他に良い実装方法ないか少し久保さんにも確認してみます。

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
        // TODO [質問] breathIn, prepareAbdominalMuscle は BarkingProcess 以外では使われない想定ですか？ by subaru (2020/05/20)
        // そのように考えたプロセスとか聞いてみたいです。
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
