package sg.edu.nus.iss.app.day12homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.day12homework.Word;
import sg.edu.nus.iss.app.day12homework.repository.CorpusRepository;

@Service
public class CorpusService {

    @Autowired
    CorpusRepository corpusRepository;
    
    public List<Word> analyze(String content) {
        corpusRepository.deleteAllWords();
        String preprocessedContent = preprocessContent(content);
        String[] strArray = preprocessedContent.split("\\s+");
        String currentWord = "";
        String nextWord = "";
        
        // i stops at array length minus 1 as the last word of the corpus will not have a next word.
        for (int i = 0; i < strArray.length - 1; i++) {
            currentWord = strArray[i].trim();
            nextWord = strArray[i+1].trim();

            if (!corpusRepository.wordExists(currentWord)) {
                corpusRepository.addWord(currentWord);
            }
            corpusRepository.updateNextWordCount(currentWord, nextWord);
        }

        return corpusRepository.getWords();
    }


    private String preprocessContent(String content) {
        return content.replaceAll("\\p{Punct}","") // remove punctuations
                .toLowerCase() // to lower case
                .replaceAll("\u2019", "") // strip off other unrecognized characters
                .replaceAll("\u201C", "") // strip off other unrecognized characters
                .replaceAll("\u2026", ""); // strip off other unrecognized characters
    }
}
