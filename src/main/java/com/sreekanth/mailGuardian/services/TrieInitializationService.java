package com.sreekanth.mailGuardian.services;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sreekanth.mailGuardian.Trie.Trie;

@Service
public class TrieInitializationService {

	
    private Trie spamTrie;
    private Trie burnerTrie;

    @Autowired
    public TrieInitializationService(@Qualifier("spamTrie") Trie spamTrie, @Qualifier("burnerTrie") Trie burnerTrie) {
        this.spamTrie = spamTrie;
        this.burnerTrie = burnerTrie;
    }

    public Trie initializeSpamTrieFromURL(String txtUrl) throws IOException {
    	
    	URL url = new URL(txtUrl);
		Scanner s = new Scanner(url.openStream());
		while (s.hasNext()) {
			
			spamTrie.insert(s.next());
		}
		s.close();

		return spamTrie;
    	
    }
    
    
    public Trie initializeBurnerTrieFromURL(String txtUrl) throws IOException {
    	
    	URL url = new URL(txtUrl);
		Scanner s = new Scanner(url.openStream());
		while (s.hasNext()) {
			burnerTrie.insert(s.next());
		}
		s.close();

		return burnerTrie;
    	
    }
    
    // Implement a method to process file content
    private List<String> processFileContent(String fileContent) {
        // Implement logic to process the file content and return a list of records
        return Arrays.asList(fileContent.split("\\s+"));
    }
 
}
