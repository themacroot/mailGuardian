package com.sreekanth.mailGuardian.schedules;

import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sreekanth.mailGuardian.services.TrieInitializationService;

@Component
public class TrieUpdateTask {

    private final TrieInitializationService trieInitializationService;

    public TrieUpdateTask(TrieInitializationService trieInitializationService) {
        this.trieInitializationService = trieInitializationService;
    }

    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    public void updateTrie() throws IOException {
    	
    	 String txtUrlSpam = "https://www.stopforumspam.com/downloads/toxic_domains_whole_filtered_250000.txt";
    	 String txtUrlBurner ="https://raw.githubusercontent.com/groundcat/disposable-email-domain-list/master/domains.txt";

        // Fetch records from the URL and update the Trie
        trieInitializationService.initializeSpamTrieFromURL(txtUrlSpam);
        trieInitializationService.initializeBurnerTrieFromURL(txtUrlBurner);
    }
}