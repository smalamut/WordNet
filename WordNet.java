
/* Name: Sara Malamut
 * NetID: smalamut
 * Precept: P04A
 * 
 * Partner Name: Josh Moskovits
 * Partner Login: jm37
 * Partner Precept: P02
 * 
 * Compilation: javac WordNet.java
 * Execution: java WordNet
 * Dependencies: StdIn.java, ST.java, DAG.java, In.java, Digraph.java
 * 
 * Description: This program implements a WordNet data type
 */

import java.lang.Object;
import java.util.*;

public class WordNet {
    
    private ST<Integer, String[]> synID; //order of id
    private ST<String, Queue<Integer>> synNoun; //order of words
    private Digraph d;
    private int vert;
    //private int edges = 0;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        
        //filenames
        In in1 = new In(synsets);
        In in2 = new In(hypernyms); 
        
        
        //Synsets as a symbol table
        synID = new ST<Integer, String[]>(); 
        
        synNoun = new ST<String, Queue<Integer>>();
        
        int key = 0;  //id
        
        //reads sysnsets.txt
        while (!in1.isEmpty()) {
            
            //reading line
            String[] fields = in1.readLine().split(",");
            key = Integer.parseInt(fields[0]);
            
            //split by spaces
            String[] r = fields[1].split(" ");
            //putting in id and strings
            synID.put(key, r);
             
            //synNoun
            for (int k = 0; k < r.length; k++) {
                if (!synNoun.contains(r[k])) { 
                    Queue<Integer> q = new Queue<Integer>();
                    q.enqueue(key);
                    synNoun.put(r[k], q);
                }
                else { //if noun already exists
                    synNoun.get(r[k]).enqueue(key);     
                    synNoun.put(r[k], synNoun.get(r[k]));   
                }
            }
            
            vert++;
            
        }
        
        d = new Digraph(vert);
        
        //reading hypernyms
        while (!in2.isEmpty()) {
            
            String[] ints = in2.readLine().split(",");
            
            int id = Integer.parseInt(ints[0]); //synset id
            
           //populating DAG
            for (int j = 1; j < ints.length; j++) {
                d.addEdge(id, Integer.parseInt(ints[j]));
            }
        }
        
        SAP s = new SAP(d);
        if (!s.isRootedDAG()) {
            throw new java.lang.IllegalArgumentException(
                "not a rooted DAG");
        }
        
    }
    
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        ST<String, Integer> st = new ST<String, Integer>();
        int c = 0; 
       
        int sum = 0;
        for (int i = 0; i < vert; i++) {
            String[] arr = synID.get(i);
           
            
            for (int j = 0; j < arr.length; j++) {
                if (!st.contains(arr[j])) //already contains
                { st.put(arr[j], j);
                    c++; }
                
            }
            sum += arr.length;
       
          
        }
         
        return st; 
    }
    
    
    
    // is the word a WordNet noun?
    public boolean isNoun(String word) {        
        
        return synNoun.contains(word);
    }
    
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!(isNoun(nounA)) || !(isNoun(nounB))) 
            throw new java.lang.IllegalArgumentException(
                "not a rooted DAG");
        
        SAP s = new SAP(d);
        
        Queue<Integer> a = new Queue<Integer>();
        a = synNoun.get(nounA); //vertices with nounA
        Queue<Integer> b = new Queue<Integer>();
        b = synNoun.get(nounB); //vertices with nounA
        
        return (s.length(a, b)); 
        
    }
    
    
    
    // a synset (second field of synsets.txt) that is the 
    // common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!(isNoun(nounA)) || !(isNoun(nounB))) 
           throw new java.lang.IllegalArgumentException(
                "not a rooted DAG");
        
        SAP s = new SAP(d);
        
        Queue<Integer> a = new Queue<Integer>();
        a = synNoun.get(nounA); //vertices with nounA
        Queue<Integer> b = new Queue<Integer>();
        b = synNoun.get(nounB); //vertices with nounA
        
        int anc = s.ancestor(a, b); //common ancestor
        String[] words = synID.get(anc); //noun of anc

        String common = words[0];
        
        for (int i = 1; i < words.length; i++) {         
            common = common.concat(" " + words[i]); 
            
        }
        
        return common;
        
    }
    
    // do unit testing of this class
    public static void main(String[] args) { 
        
        
        String synsets = "synsets.txt"; 
        String hypernyms = "hypernyms.txt"; 
        
        WordNet wordnet = new WordNet(synsets, hypernyms);
        
        System.out.println("sap " + wordnet.sap("leveller", "Houses_of_Parliament"));
       System.out.println("nouns() " + wordnet.nouns());
        wordnet.nouns();
        // System.out.println("isnoun... " + wordnet.isNoun("cccc"));
        
        
    }
}