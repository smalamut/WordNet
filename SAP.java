

/* Name: Sara Malamut
 * 
 * Compilation: javac SAP.java
 * Execution: java SAP
 * Dependencies: StdIn.java, ST.java, DAG.java, In.java,
 *        Topological.java, TransitiveClosure.java, BreadthFirstDirectedPaths.java,
 *        Digraph.java,
 * 
 * Description: This program implements the data type SAP 
 */

public class SAP {
    
    private Digraph G;

   // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph H) { 
        G = new Digraph(H);
    }

   // is the digraph a directed acyclic graph?
    public boolean isDAG() {
        DirectedCycle dg = new DirectedCycle(G);
        return (!dg.hasCycle());
    }

   // is the digraph a rooted DAG?
    public boolean isRootedDAG() {
        int root = 0;
        Topological t = new Topological(G);
        TransitiveClosure tc = new TransitiveClosure(G);
        if (!isDAG()) return false;
        else {
            Iterable<Integer> top = t.order();
            for (int s : top) {
                root = s;
            }
            for (int s : top) {
                if (!tc.reachable(s, root)) return false;
            }
            return true;
        }
    }
            
        

   // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths c = new BreadthFirstDirectedPaths(G, w);
        int closest = 0;
        int min = (int) Integer.MAX_VALUE;
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < G.V(); i++) {
            if (b.hasPathTo(i) && c.hasPathTo(i)) {
                count = b.distTo(i);
                count2 = c.distTo(i);
                if (count + count2 < min) {
                    min = count + count2;
                    closest = i;
                }
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        else return min;
    }
        
   // a common ancestor of v and w that participates in a 
    // shortest ancestral path;
    //-1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths c = new BreadthFirstDirectedPaths(G, w);
        int closest = 0;
        int min = (int) Integer.MAX_VALUE;
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < G.V(); i++) {
            if (b.hasPathTo(i) && c.hasPathTo(i)) {
                count = b.distTo(i);
                count2 = c.distTo(i);
                if (count + count2 < min) {
                    min = count + count2;
                    closest = i;
                }
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        else return closest;
    }
        
        
   // length of shortest ancestral path between any vertex in 
    // v and any vertex in w; 
    //-1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths c = new BreadthFirstDirectedPaths(G, w);
        int closest = 0;
        int min = Integer.MAX_VALUE;
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < G.V(); i++) {
            if (b.hasPathTo(i) && c.hasPathTo(i)) {
                count = b.distTo(i);
                count2 = c.distTo(i);
                if (count + count2 < min) {
                    min = count + count2;
                    closest = i;
                }
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        else return min;
    }
        

   // a common ancestor that participates in shortest ancestral path; 
    // -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths c = new BreadthFirstDirectedPaths(G, w);
        int closest = 0;
        int min = (int) Integer.MAX_VALUE;
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < G.V(); i++) {
            if (b.hasPathTo(i) && c.hasPathTo(i)) {
                count = b.distTo(i);
                count2 = c.distTo(i);
                if (count + count2 < min) {
                    min = count + count2;
                    closest = i;
                }
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        else return closest;
    }
    
   // do unit testing of this class
    public static void main(String[] args) {
      
    In in = new In(args[0]);
    
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    
    while (!StdIn.isEmpty()) {
        
        int v = StdIn.readInt();
        int w = StdIn.readInt();
        
        int length   = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
}
}
