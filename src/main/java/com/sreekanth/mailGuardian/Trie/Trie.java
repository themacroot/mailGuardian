package com.sreekanth.mailGuardian.Trie;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;




@Component

public class Trie {
	

	@Autowired
	private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }

        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }

        return current.isEndOfWord;
    }

    private TrieNode searchNode(String word) {
    	
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.getChild(ch);
            
            if (node == null) {
                return null; // Prefix not found
            }
        }
System.out.println(node.getValue());
        return node;
    }
    @Component
    private static class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isEndOfWord;
        private String value;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isEndOfWord = false;
            this.value = null;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public TrieNode getChild(char ch) {
            return children.get(ch);
        }

        public void setEndOfWord(boolean endOfWord) {
            isEndOfWord = endOfWord;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}