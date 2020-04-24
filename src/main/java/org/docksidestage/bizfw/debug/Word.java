package org.docksidestage.bizfw.debug;

/**
 * @author zaya
 * @author hayato.sasaki
 */
public class Word {
    private Language language;
    private String word;

    public Word(Language language, String word) {
        this.language = language;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public Language getLanguage() {
        return language;
    }

    public boolean hasLanguage() {
        if (getLanguage() == null) {
            return false;
        } else {
            return true;
        }
    }

    // wordの中身を置き換えるメソッドを追加
    public String replaceWord(String target, String replacement) {
        word = word.replace(target, replacement);
        return word;
    }
}
