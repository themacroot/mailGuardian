package com.sreekanth.mailGuardian.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sreekanth.mailGuardian.Trie.Trie;

@Service
public class TrieSearchService {

	private Trie burnerTrie;
	private Trie spamTrie;

	@Autowired
	public TrieSearchService(@Qualifier("spamTrie") Trie spamTrie, @Qualifier("burnerTrie") Trie burnerTrie) {
		this.spamTrie = spamTrie;
		this.burnerTrie = burnerTrie;
	}

	public boolean searchInTrieSpam(String key) {

		if (spamTrie.search(key)) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false");
			return false;
		}
	}

	public boolean searchInTrieBurner(String key) {
		if (burnerTrie.search(key)) {
			return true;
		} else {
			System.out.println("nf");
			return false;
		}
	}
}
