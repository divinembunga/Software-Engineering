// Java Program for Lowest Common Ancestor in a Directed Acyclic Graph
// A O(n) solution to find LCA of two given values n1 and n2 
// This code is credited to Devin Connolly.
import java.util.*;

public class lcaDag {
	private int V;           
	private int E;              
	private ArrayList<Integer>[] adj;    
	private int[] indegree;        
	private boolean marked[];		//tracks visited vertices
	private boolean hasCycle;		//Will be true if there is a cycle in graph
    private boolean stack[];		//The order in which vertices were visited
    private int[] edgeTo;      // last edge on shortest s->v path
    private int[] distTo;      // length of shortest s->v path

    
	public lcaDag(int V)
	{
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
	    this.V = V;
	    this.E = 0;
	    indegree = new int[V];
	    marked = new boolean[V];
	    stack = new boolean[V];
	    adj = (ArrayList<Integer>[]) new ArrayList[V];
	    for (int v = 0; v < V; v++) {
	        adj[v] = new ArrayList<Integer>();
	    }              
	}

	//Returns current vertex
	public int V() {
		return V;	
	}
	
	public int E() {
        return E;
    }

	
	
	//Adds a directed edge from v->w
	public void addEdge(int v, int w)
	{
	    if((validateVertex(v)>0)&&(validateVertex(w)>0))
	    {
	    	adj[v].add(w);
	    	indegree[w]++;
	    	E++;
	    }
	    else{
	    	System.out.println("Please enter vertices between 0 & n-1");
	    }
	    	
	}
	
	private int validateVertex(int v) {
        if (v < 0 || v >= V)
        	return -1;
        else
        	return 1;}

	
	//Returns amount of directed edges incident to vertex v
	public int indegree(int v) {
		if(validateVertex(v)<0){
			return -1;
		}
		else{
			return indegree[v];
		}
	}
	
	//Returns amount of directed edges from vertex v
	public int outdegree(int v) {
		if(validateVertex(v)<0){
			return -1;
		}
		else{
			return adj[v].size();
		}
    }
		
	
	//Returns the adjacent vertices to v
	public Iterable<Integer> adj(int v)
	{ return adj[v]; }
	
	
	
	public boolean hasCycle() {

        return hasCycle;
    }
	
	 public void findCycle(int v) {

	        marked[v] = true;
	        stack[v] = true;

	        for (int w : adj(v)) {
	            if(!marked[w]) {
	                findCycle(w);
	            } else if (stack[w]) {
	                hasCycle = true;
	                return;
	            }
	        }

	        stack[v] = false;
	    }
	 
	 public ArrayList<Integer> BFS(int s)
	    {
	        // Mark all the vertices as not visited(default set as false)
	        boolean visited[] = new boolean[V];
	 
	        LinkedList<Integer> queue = new LinkedList<Integer>();
	        ArrayList<Integer> order= new ArrayList<Integer>();
	 
	        visited[s]=true;
	        queue.add(s);
	        
	 
	        while (queue.size() != 0)
	        {
	            // Dequeue a vertex from queue and print it
	            s = queue.poll();           
	            order.add(s);
	            // Get all adjacent vertices of the dequeued vertex s
	            // If a adjacent has not been visited, then mark it
	            // visited and enqueue it
	            Iterator<Integer> i = adj[s].listIterator();
	            while (i.hasNext())
	            {
	                int n = i.next();
	                if (!visited[n])
	                {
	                    visited[n] = true;
	                    queue.add(n);
	                }
	            }
	        }
	        
	        return order;
	        
	    }
	 
	 public int findLCA(int v, int w){
			findCycle(0);
			if(hasCycle){
				//this graph is not a directed acyclic graph
				return -1;
			}
			lcaDag backwards = reverse();
			ArrayList<Integer> vPath = backwards.BFS(v);
			ArrayList<Integer> wPath = backwards.BFS(w);
			ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
			boolean found = false;
			for(int i = 0; i<vPath.size(); i++){
				for(int t = 0; t<wPath.size(); t++){		
					if(vPath.get(i)==wPath.get(t)){
						commonAncestors.add(vPath.get(i));
						found=true;
						}
				}
			}
			
			if(found)
				return commonAncestors.get(0);
			else
				return -1;
		}
	 
	 public lcaDag reverse() {
		 lcaDag reverse = new lcaDag(V); 
	        for (int v = 0; v < V; v++) {
	            for (int w : adj(v)) {
	                reverse.addEdge(w, v); //reverse the direction of the edges
	            }
	        }
	        return reverse;
	    }
	 
}
