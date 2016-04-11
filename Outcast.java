
/* Name: Sara Malamut
 * NetID: smalamut
 * Precept: P04A
 * 
 * Partner Name: Josh Moskovits
 * Partner Login: jm37
 * Partner Precept: P02
 * 
 * Compilation: javac SAP.java
 * Execution: java SAP
 * Dependencies: StdIn.java, ST.java, DAG.java, In.java,
 *        Topological.java, TransitiveClosure.java, BreadthFirstDirectedPaths.java,
 *        Digraph.java,
 * 
 * Description: This program implements the data type SAP 
 */
public class Outcast {
    
    private WordNet w;
    
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        w = wordnet;
        
    }
    
   // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        
        String p = "";
        int max = 0;
        for (int i = 0; i < nouns.length; i++) {
            int distance = 0;
            for (int j = 0; j < nouns.length; j++) {
                distance += w.distance(nouns[i], nouns[j]);
            }
            
            if (distance > max) {
                max = distance;
                p = nouns[i];
            }
        }
        return p;
    }
    
    // see test client below
    public static void main(String[] args) {
        
    WordNet wordnet = new WordNet(args[0], args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
        In in = new In(args[t]);
        String[] nouns = in.readAllStrings();
        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    }
}
}