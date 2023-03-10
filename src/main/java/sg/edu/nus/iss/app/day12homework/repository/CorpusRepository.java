package sg.edu.nus.iss.app.day12homework.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.day12homework.Word;

@Repository
public class CorpusRepository {

    private List<String> uniqueWords = new ArrayList<>();
    private List<Word> words = new ArrayList<>();

    public boolean wordExists(String word) {
        return uniqueWords.contains(word);
    }

    public void addWord(String word) {
        uniqueWords.add(word);
        words.add(new Word(word));
    }

    public void updateNextWordCount(String currentWord, String nextWord) {
        words.get(uniqueWords.indexOf(currentWord)).updateNextWordCount(nextWord);
    }

    public List<Word> getWords() {
        return words;
    }

    // called at the start of the CorpusService's analyze method so as to clear previous analysis
    public void deleteAllWords(){
        uniqueWords.clear();
        words.clear();
    }
}
