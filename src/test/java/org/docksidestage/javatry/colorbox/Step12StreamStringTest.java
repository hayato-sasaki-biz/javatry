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
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author hayato.sasaki
 */
public class Step12StreamStringTest extends PlainTestCase {
    // TODO 全体的に気になったこと by subaru (2020/05/21)
    // log 出力の仕方が気になります。
    // 特に複数回答が想定されていて、出力内容が回答の List オブジェクトのみというものがいくつかありますが、
    // 実行しても初見の人はよくわからないので、出力の形式を工夫してみてください。

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .max(Comparator.comparing(String::length))
                .orElse("*not found");
        log(longestString);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> stringContentList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .collect(Collectors.toList());
        String longestString = stringContentList.stream().max(Comparator.comparing(String::length)).orElse(null);
        String shortestString = stringContentList.stream().min(Comparator.comparing(String::length)).orElse(null);

        if (longestString == null) {    // longestString == nullならばshortestStringも同様にnull
            log("There may be no texts in the boxes.");
        } else {
            log(longestString);
            log(shortestString);
            int maxMinDiff = longestString.length() - shortestString.length();

            log(maxMinDiff);
        }
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String secondLongestString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .map(content -> content.toString())
                .sorted(Comparator.comparing(String::length).reversed())
                .skip(1)
                .findFirst()
                .orElse("*not found");
        log(secondLongestString);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int accumLength = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .reduce((accum, value) -> accum + value)
                .map(reduced -> reduced.length())
                .orElse(0);
        log(accumLength);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestColorName = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor().toString())
                .max(Comparator.comparing(String::length))
                .orElse("*not found");
        log(longestColorName);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxColor> colorList = colorBoxList.stream().filter(
                // あるcolorBoxが"Water"で始まる文字列をしまっているか判定
                colorBox -> {
                    // DONE ここは anyMatch を使用するともう少しシンプルにかけます。 by subaru (2020/05/21)
                    return colorBox.getSpaceList()
                            .stream()
                            .map(boxSpace -> boxSpace.getContent())
                            .filter(content -> content instanceof String)
                            .anyMatch(content -> content.toString().startsWith("Water"));
                }).map(colorBox -> colorBox.getColor()).collect(Collectors.toList());
        // DONE ここは colorList だね。 by subaru (2020/05/21)
        // ただし colorList を単に log で出力するのは少しみにくいので、log の出力も工夫してみましょう。
        //log(colorList);
        log("Waterで始まるカラーボックスの一覧");
        for (BoxColor color : colorList) {
            log("\t* " + color);
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxColor> colorList = colorBoxList.stream().filter(
                // あるcolorBoxが"front"で終わる文字列をしまっているか判定
                colorBox -> {
                    // DONE こちらも anyMatch を使ってみてください by subaru (2020/05/21)
                    return colorBox.getSpaceList()
                            .stream()
                            .map(boxSpace -> boxSpace.getContent())
                            .filter(content -> content instanceof String)
                            .anyMatch(content -> content.toString().endsWith("front"));
                }).map(colorBox -> colorBox.getColor()).collect(Collectors.toList());
        log("Waterで始まるカラーボックスの一覧");
        for (BoxColor color : colorList) {
            log("\t* " + color);
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String query = "front";
        List<Integer> indexList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .filter(contentString -> contentString.endsWith(query))
                .map(contentString -> contentString.indexOf(query) + 1)
                .collect(Collectors.toList());
        log(indexList);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        char query = 'ど';
        List<Integer> indexList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .filter(
                        // 'ど'を2つ以上含むかを判定
                        contentString -> {
                            long queryCount = contentString.chars().filter(ch -> ch == query).count();
                            return queryCount >= 2;
                        })
                .map(contentString -> contentString.lastIndexOf(query) + 1)
                .collect(Collectors.toList());
        log(indexList);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String query = "front";
        // DONE この書き方でも大丈夫だけどせっかくなので substring も使ってみましょう。 by subaru (2020/05/21)
        log(query + "で終わる文字列(一覧):");
        List<String> firstLetterList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .filter(contentString -> contentString.endsWith(query))
                .peek(contentString -> log(contentString))
                .map(contentString -> contentString.substring(0, 1))
                .collect(Collectors.toList());
        // DONE ここの log 出力、もとの文字列が何だったかもみれるとわかりやすいですね。 by subaru (2020/05/21)
        log("frontで終わる文字列の最初の一文字(一覧): ");
        firstLetterList.forEach(firstLetter -> log(firstLetter));
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String query = "Water";
        log(query + "で始まる文字列(一覧):");
        List<String> lastLetterList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .filter(contentString -> contentString.startsWith(query))
                .peek(contentString -> log(contentString))
                .map(contentString -> contentString.substring(contentString.length() - 1))
                .collect(Collectors.toList());
        log(query + "で始まる文字列の最後の一文字(一覧): ");
        lastLetterList.forEach(lastLetter -> log(lastLetter));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String query = "o";
        // DONE 一点 String のキャスト不要なので削除しましょう。 by subaru (2020/05/21)
        List<Integer> queryRemovedLengthList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .filter(contentString -> contentString.contains(query))
                .map(contentString -> contentString.replace(query, ""))
                .map(contentString -> contentString.length())
                .collect(Collectors.toList());
        log(queryRemovedLengthList);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> queryRemovedLengthList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof File)
                .map(content -> (File) content)
                .map(file -> file.getPath())
                .map(filePath -> filePath.replace("/", "\\"))
                .collect(Collectors.toList());
        log(queryRemovedLengthList);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        // DONE こちらよく書けています！ by subaru (2020/05/21)
        // このままでもいいけど、一応補足すると peek メソッドというものがあります。
        // これを使用すると途中で devilBoxList というローカル変数に切り出したりせずに書く事ができます。
        // 修正しなくても大丈夫ですが、余裕があれば調べてみてください！
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // DevilBoxのリストを取得
        Integer accumulatedTextLength = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof YourPrivateRoom.DevilBox)
                .map(content -> (YourPrivateRoom.DevilBox) content)
                .peek(devilBox -> {
                    devilBox.wakeUp();
                    devilBox.allowMe();
                    devilBox.open();
                })
                .map(devilBox -> {
                    try {
                        return devilBox.getText();
                    } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                        return "";
                    }
                }).reduce((accum, value) -> accum + value).map(concatText -> concatText.length()).orElse(0);
        log(accumulatedTextLength);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
