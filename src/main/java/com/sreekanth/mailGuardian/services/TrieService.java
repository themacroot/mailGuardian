package com.sreekanth.mailGuardian.services;

import com.sreekanth.mailGuardian.Trie.Trie;

public class TrieService {


    private Trie trieSpam;
    private Trie trieBurner;

    public TrieService(Trie trieSpam, Trie trieBurner) {
        this.trieSpam = trieSpam;
        this.trieBurner = trieBurner;
    }

    public void insertIntoTrieSpam( String value) {
        trieSpam.insert( value);
    }

    public void insertIntoTrieBurner( String value) {
        trieBurner.insert(value);
    }

    public boolean searchInTrieSpam(String key) {
        return trieSpam.search(key);
    }

    public boolean searchInTrieBurner(String key) {
        return trieBurner.search(key);
    }
}
