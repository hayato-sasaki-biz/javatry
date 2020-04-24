package org.docksidestage.bizfw.debug;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zaya
 * @author hayato.sasaki
 */
public class WordPool {
    private final Map<Long, Word> wordMap;

    public WordPool() {
        LanguagePool languagePool = new LanguagePool();
        wordMap = new HashMap<>();
        wordMap.put(1L, new Word(getJapanese(languagePool), "私"));
        wordMap.put(2L, new Word(getJapanese(languagePool), "柿"));
        wordMap.put(3L, new Word(getJapanese(languagePool), "荼"));
        wordMap.put(4L, new Word(getJapanese(languagePool), "昴"));
    }

    public Map.Entry<Long, Word> create(Language language, String word) {
        Long id = incrementId();
        wordMap.put(id, new Word(language, word));
        return new AbstractMap.SimpleEntry<>(id, find(id));
    }

    // NOTE 上のcreateで呼び出されているので、定義の位置を近くに移動
    public Word find(Long id) {
        return wordMap.get(id);     // NOTE removeからgetに修正
    }

    public Word find(String word) {
        return wordMap.values().stream().filter(v -> v.getWord().equals(word)).findFirst().orElseThrow(
                () -> new NoSuchElementException("No such word : " + word)
        );  // 例外メッセージを追加
    }

    public Long findId(String word) {
        return wordMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getWord().equals(word))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such word: " + word));
    }

    public Word update(Long id, Word word) {
        wordMap.remove(id);
        wordMap.put(id, word);
        return wordMap.get(id);
    }

    // NOTE: 修正前の引数 word1, word2はそれぞれの役割が分かりづらいので、名前を変更
    public Word update(String oldWord, String newWord) {
        Long id = findId(oldWord);
        Word word = wordMap.get(id);
        wordMap.remove(id);
        wordMap.put(id, new Word(word.getLanguage(), newWord));
        return wordMap.get(id);
    }

    public Word update(String language, String word1, String word2) {
        Long id = findId(word2);
        wordMap.remove(id);
        wordMap.put(id, new Word(new Language(language), word1));
        return wordMap.get(id);
    }

    // 引数名を変更
    // idが存在しない場合に例外を投げるように変更
    // 新しく追加したWord.replaceWordを使うように変更
    public String replace(Long id, String target, String replacement) {
        Word replacedElem = wordMap.get(id);
        if (replacedElem == null) {
            throw new NoSuchElementException("No such id in wordMap : id = " + id);
        }
        return replacedElem.replaceWord(target, replacement);
    }

    public void delete(Long id) {
        wordMap.remove(id);
    }

    public List<Word> getWords() {
        return new ArrayList<>(wordMap.values());
    }

    public List<String> getWordsOnly() {
        return wordMap.values().stream().map(Word::getWord).collect(Collectors.toList());
    }

    public List<Language> getLanguages() {
        List<Language> languages = new ArrayList<>();
        wordMap.forEach((id, word) -> {
            if (!word.hasLanguage()) {
                throw new IllegalStateException("言語がないよ〜 word: " + word);
            }
            languages.add(word.getLanguage());
        });
        return languages;
    }

    private Long incrementId() {
        return findMaxId() + 1;
    }

    private Language getJapanese(LanguagePool languagePool) {
        return languagePool.getLanguage("Japanese");
    }

    private Long findMaxId() {
        return wordMap.keySet().stream().mapToLong(k -> k).max().orElseThrow(NoSuchElementException::new);
    }
}
