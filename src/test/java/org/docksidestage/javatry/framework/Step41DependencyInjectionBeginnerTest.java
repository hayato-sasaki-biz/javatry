/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.framework;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.screw.SpecialScrewManufacturer;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.di.container.SimpleDiContainer;
import org.docksidestage.bizfw.di.nondi.NonDiDirectFirstAction;
import org.docksidestage.bizfw.di.nondi.NonDiDirectSecondAction;
import org.docksidestage.bizfw.di.usingdi.*;
import org.docksidestage.bizfw.di.usingdi.settings.UsingDiModule;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Dependency Injection (DI) as beginner level. <br>
 * Show answer by log() or write answer on comment for question of javadoc.
 * @author jflute
 * @author hayato.sasaki
 */
public class Step41DependencyInjectionBeginnerTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        Precondition
    //                                                                        ============
    /**
     * Search "Dependency Injection" by internet and learn it in thirty minutes. (study only) <br>
     * ("Dependency Injection" をインターネットで検索して、30分ほど学んでみましょう。(勉強のみ))
     */
    public void test_whatis_DependencyInjection() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // What is Dependency Injection?
        // - - - - - (your answer?)
        // DIはインスタンスの管理を行うデザインパターン
        // 管理 = インスタンスの生成 と ライフサイクルの管理
        // -> DIコンテナから必要なインスタンスを簡単に取り出すことができる
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                                 Non DI Code Reading
    //                                                                 ===================
    /**
     * What is the difference between NonDiDirectFirstAction and NonDiDirectSecondAction? <br>
     * (NonDiDirectFirstAction と NonDiDirectSecondAction の違いは？)
     */
    public void test_nondi_difference_between_first_and_second() {
        // your answer? => ともにDogやSupercarのインスタンスをメソッドの内部で生成しているが、
        //                 secondではそれらのクラスのサブクラスのインスタンスを生成している
        // and your confirmation code here freely
        log("=== NonDiDirectFirstAction ===");
        NonDiDirectFirstAction firstAction = new NonDiDirectFirstAction();
        log("=== call friend ===");
        firstAction.callFriend();
        log("=== wake up me ===");
        firstAction.wakeupMe();
        // NOTE ↑の2つのメソッドは全く同じことをやっている。
        // また、内部でDogのインスタンスを生成している
        log("=== go to office ===");
        try {
            firstAction.goToOffice();
        } catch (SpecialScrewManufacturer.SpecialScrewCannotMakeBySpecException e) {
            log("Exception!!");
            log(e.getMessage());
        }
        log("=== send gift ===");
        try {
            firstAction.sendGift();
        } catch (SpecialScrewManufacturer.SpecialScrewCannotMakeBySpecException e) {
            log("Exception!!");
            log(e.getMessage());
        }

        log("\n=== NonDiDirectSecondAction ===");
        NonDiDirectSecondAction secondAction = new NonDiDirectSecondAction();
        log("=== call friend ===");
        try {
            secondAction.callFriend();
        } catch (IllegalStateException e) {
            log("Exception!!");
            log(e.getMessage());
        }
        log("=== wake up me ===");
        try {
            secondAction.wakeupMe();
        } catch(IllegalStateException e) {
            log("Exception!!");
            log(e.getMessage());
        }
        // NOTE DogではなくTooLazyDog(Dogのサブクラス)のインスタンスをメソッド内部で生成している
        log("=== go to office ===");
        try {
            secondAction.goToOffice();
        } catch (SpecialScrewManufacturer.SpecialScrewCannotMakeBySpecException e) {
            log("Exception!!");
            log(e.getMessage());
        }
        log("=== send gift ===");
        try {
            secondAction.sendGift();
        } catch (SpecialScrewManufacturer.SpecialScrewCannotMakeBySpecException e) {
            log("Exception!!");
            log(e.getMessage());
        }
    }

    /**
     * What is the difference between NonDiDirectSecondAction and NonDiFactoryMethodAction? <br>
     * (NonDiDirectSecondAction と NonDiFactoryMethodAction の違いは？)
     */
    public void test_nondi_difference_between_second_and_FactoryMethod() {
        // your answer? => メソッド内部でのインスタンス生成方法が異なる
        // * NonDiDirectSecondActionの場合: newを直接使う
        // * NonDiFactoryMethodActionの場合: インスタンスの生成のために必要なプロセスをcreate〇〇というメソッドにまとめている
        // and your confirmation code here freely
    }

    /**
     * What is the difference between NonDiFactoryMethodAction and NonDiIndividualFactoryAction? <br>
     * (NonDiFactoryMethodAction と NonDiIndividualFactoryAction の違いは？)
     */
    public void test_nondi_difference_between_FactoryMethod_and_IndividualFactory() {
        // your answer? => factoryメソッドの定義位置が異なる
        // NonDiFactoryActionの場合: NonDiFactoryMethodクラス内でインスタンスの生成方法を定義している
        // NonDiIndividualFactoryActionの場合: NonDiIndividualFactoryMethodクラスの外でインスタンスの生成方法を定義している
        // and your confirmation code here freely
    }

    // ===================================================================================
    //                                                               Using DI Code Reading
    //                                                               =====================
    /**
     * What is the difference between UsingDiAccessorAction and UsingDiAnnotationAction? <br>
     * (UsingDiAccessorAction と UsingDiAnnotationAction の違いは？)
     */
    public void test_usingdi_difference_between_Accessor_and_Annotation() {
        // your answer? => 依存しているインスタンスをどのように紐付けるかが異なる
        // * UsingDiAccessorActionの場合: setterを用いてインスタンス同士を紐付ける
        // * UsingDiAnnocationActionの場合: @SimpleInjectのようなアノテーションを用いてインスタンス同士を紐付ける
        // and your confirmation code here freely
        log("=== UsingDiAccessorAction ===");
        UsingDiAccessorAction accessorAction = new UsingDiAccessorAction();
        Animal dog = new Dog();
        accessorAction.setAnimal(dog);
        accessorAction.callFriend();

        log("=== UsingDiAnnotationAction ===");
        // SimpleDiContainerはsingletonなのでnewではなくgetInstance()
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
            // injectされるインスタンス
            componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
            // injectするインスタンス
            componentMap.put(Animal.class, new Dog());
            componentMap.put(SupercarDealer.class, new SupercarDealer());
        });
        diContainer.resolveDependency();

        UsingDiAnnotationAction diAnnotationAction =
                (UsingDiAnnotationAction) diContainer.getComponent(UsingDiAnnotationAction.class);
        diAnnotationAction.callFriend();
    }

    /**
     * What is the difference between UsingDiAnnotationAction and UsingDiDelegatingAction? <br>
     * (UsingDiAnnotationAction と UsingDiDelegatingAction の違いは？)
     */
    public void test_usingdi_difference_between_Annotation_and_Delegating() {
        // your answer? =>
        // * UsingDiAnnotationActionの場合: 依存しているクラスを明確に示している (Animal, Supercar)
        // * UsingDiDelegatingActionの場合: 依存しているクラスとその機能を分離している (振る舞いだけほしい？)
        // and your confirmation code here freely
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
            componentMap.put(UsingDiDelegatingAction.class, new UsingDiDelegatingAction());
            componentMap.put(UsingDiDelegatingLogic.class, new UsingDiDelegatingLogic());
            componentMap.put(Animal.class, new Dog());
            componentMap.put(SupercarDealer.class, new SupercarDealer());
        });
        diContainer.resolveDependency();

        UsingDiDelegatingAction diDelegatingAction =
                (UsingDiDelegatingAction) diContainer.getComponent(UsingDiDelegatingAction.class);
        diDelegatingAction.callFriend();
    }

    // ===================================================================================
    //                                                           Execute like WebFramework
    //                                                           =========================
    /**
     * Execute callFriend() of accessor action by UsingDiWebFrameworkProcess. <br>
     * (accessor の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう)
     */
    public void test_usingdi_UsingDiWebFrameworkProcess_callfriend_accessor() {
        // execution code here
        UsingDiWebFrameworkProcess frameworkProcess = new UsingDiWebFrameworkProcess();
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
            componentMap.put(UsingDiAccessorAction.class, new UsingDiAccessorAction());
            componentMap.put(Animal.class, new Dog());
            componentMap.put(SupercarDealer.class, new SupercarDealer());
        });
        diContainer.resolveDependency();
        frameworkProcess.requestAccessorCallFriend();
    }

    /**
     * Execute callFriend() of annotation and delegating actions by UsingDiWebFrameworkProcess.
     * (And you can increase hit-points of sleepy cat in this method) <br>
     * (annotation, delegating の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう。
     * (眠い猫のヒットポイントをこのメソッド内で増やしてもOK))
     */
    public void test_usingdi_UsingDiWebFrameworkProcess_callfriend_annotation_delegating() {
        // execution code here
        UsingDiWebFrameworkProcess frameworkProcess = new UsingDiWebFrameworkProcess();
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(new UsingDiModule());
        diContainer.resolveDependency();

        log("=== annotation call ===");
        frameworkProcess.requestAnnotationCallFriend();
        try {
            log("=== delegating call ===");
            // WIP 猫のヒットポイントの増やし方が分からないので一旦例外処理で対応 by sasaki (20/05/21)
            frameworkProcess.requestDelegatingCallFriend();
        } catch (IllegalStateException e) {
            log(e.getMessage());
        }
    }

    /**
     * What is concrete class of instance variable "animal" of UsingDiAnnotationAction? (when registering UsingDiModule) <br>
     * (UsingDiAnnotationAction のインスタンス変数 "animal" の実体クラスは？ (UsingDiModuleを登録した時))
     */
    public void test_usingdi_whatis_animal() {
        // your answer? => TooLazyDog
        // and your confirmation code here freely
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(new UsingDiModule());
        diContainer.resolveDependency();

        log(diContainer.getComponent(Animal.class));
    }

    // ===================================================================================
    //                                                                        DI Container
    //                                                                        ============
    /**
     * What is DI container? <br>
     * (DIコンテナとは？)
     */
    public void test_whatis_DIContainer() {
        // your answer? => 外部から依存を注入する際に、その注入するインスタンスを管理するもの
        // and your confirmation code here freely
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        // DIコンテナで管理したいインスタンスを登録
        diContainer.registerModule(new UsingDiModule());
        // DIコンテナに登録した依存関係を調べ、依存の注入を行う
        diContainer.resolveDependency();
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Lasta Di application? <br>
     * (以下のLasta DiアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/lastaflute/lastaflute-example-harbor
     */
    public void test_zone_search_component_on_LastaDi() {
        // your answer? => src/main/resources/app.xml および src/main/resources/dbflute.xml
        // NOTE http://dbflute.seasar.org/ja/lastaflute/lastadi/index.html#richcomp
    }

    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Spring application? <br>
     * (以下のSpringアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/dbflute-example/dbflute-example-on-springboot
     */
    public void test_zone_search_component_on_Spring() {
        // your answer? => 
    }
}
