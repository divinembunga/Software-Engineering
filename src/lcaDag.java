import java.io.*; 
import java.util.*; 
public class lcaDag {
	 static final int INF=Integer.MAX_VALUE; 
	    class AdjListNode 
	    { 
	        private int v; 
	        private int weight; 
	        AdjListNode(int _v, int _w) { v = _v;  weight = _w; } 
	        int getV() { return v; } 
	        int getWeight()  { return weight; } 
	    } 

	 class Graph 
	    { 
	        private int V; 
	        private LinkedList<AdjListNode>adj[]; 
	        Graph(int v) 
	        { 
	            V=v; 
	            adj = new LinkedList[V]; 
	            for (int i=0; i<v; ++i) 
	                adj[i] = new LinkedList<AdjListNode>(); 
	        } 
	        void addEdge(int u, int v, int weight) 
	        { 
	            AdjListNode node = new AdjListNode(v,weight); 
	            adj[u].add(node);// Add v to u's list 
	        } 
	        Graph newGraph(int number) 
	        { 
	            return new Graph(number); 
	        } 
	      

   }
}
