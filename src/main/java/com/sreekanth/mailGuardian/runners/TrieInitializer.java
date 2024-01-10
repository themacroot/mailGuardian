package com.sreekanth.mailGuardian.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sreekanth.mailGuardian.services.TrieInitializationService;

@Component
public class TrieInitializer implements CommandLineRunner {

    private final TrieInitializationService trieInitializationService;

    @Autowired
    public TrieInitializer(TrieInitializationService trieInitializationService) {
        this.trieInitializationService = trieInitializationService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize the Trie from the URL when the application starts
   	 String txtUrlSpam = "https://www.stopforumspam.com/downloads/toxic_domains_whole_filtered_250000.txt";
   	 String txtUrlBurner ="https://raw.githubusercontent.com/groundcat/disposable-email-domain-list/master/domains.txt";

       // Fetch records from the URL and update the Trie
     trieInitializationService.initializeBurnerTrieFromURL(txtUrlBurner);
       trieInitializationService.initializeSpamTrieFromURL(txtUrlSpam);
     
    }
}