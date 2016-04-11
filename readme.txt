/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

    Name: Sara Malamut
    Login: smalamut   
    Precept: P04A

    Partner name: Josh Moskovits     
    Partner login: jm37    
    Partner precept: P02 


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **********************************************************************/
 A <Integer, String[]> symbol table, so we could cycle through every vertex
    with a for loop. A second <String, Queue<Integer>> symbol table was 
    used to easily access the integers associated with each noun.


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **********************************************************************/
   A directed acyclic graph, because that mimics the structure of the
    hypernym relationships.


/**********************************************************************
 *  Describe concisely the algorithm you used to check if the digraph 
 *  is rooted and the algorithm you used to check if the digraph is a DAG.  
 *  What is the order of growth of the worst-case running times of
 *  your algorithms as a function of the number of vertices V and the 
 *  number of edges E in the digraph?
 **********************************************************************/

Description: We first ran DirectedCycle to see if the graph was acyclic.
    If so, we found the topological order, identified the first element in
    reverse postorder, and cycled through each element (using TransitiveClosure)
    to see if it was connected to the ancestor element.


                           running time
method                      worst case
---------------------------------------
isDAG()                    E + V

isRootedDAG()              V(E + V)



/**********************************************************************
 *  Describe concisely your algorithm to compute the shortest ancestral
 *  path in SAP.java? What is the order of growth of the worst-case
 *  running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 *
 *  Be careful! It is very easy to get these wrong. Keep in mind
 *  what the best case and worst case entail. Don't forget about
 *  the fact that creating a BreadthFirstDirectedPaths object
 *  involves initializing the marked[], edgeTo[], and distTo[] arrays.
 **********************************************************************/

Description: Created two BreadthFirstDirectedPaths
    objects with vertices v, w. 
    For each vertex i in Digraph, checked if paths existed to v and w
    from i, and then computed path distance, keeping track of vertex 
    with shortest combined distance to. Same algorithm applies if
    v and w were iterables of vertices instead of single ones.

                                              running time
method                               best case            worst case
------------------------------------------------------------------------
        length(int v, int w)         V(E + V)             V(E + V)

        ancestor(int v, int w)       V(E + V)             V(E + V)

length(Iterable<Integer> v,
       Iterable<Integer> w)          V(E + V)             V(E + V)

ancestor(Iterable<Integer> v,
         Iterable<Integer> w)        V(E + V)             V(E + V)



/**********************************************************************
 *  If you implemented any extra credit optimizations, describe
 *  them here.
 **********************************************************************/




/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/
We have followed the proper protocol



/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/