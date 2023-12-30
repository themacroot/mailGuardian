package com.sreekanth.mailGuardian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.sreekanth.mailGuardian.Trie.Trie;

@Configuration
public class TrieConfig {
	
	 @Bean(name = "spamTrie")
    public Trie spamTrie() {
        // Initialize and return your first Trie instance
        return new Trie();
    }

	 @Bean(name = "burnerTrie")
    public Trie burnerTrie() {
        // Initialize and return your second Trie instance
        return new Trie();
    }

}
