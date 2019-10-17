import static org.junit.Assert.*;

import org.junit.Test;

public class lcaDagTest {
	
	@Test
	public void testForDirectedGraph(){
		lcaDag test = new lcaDag(10);
	    test.addEdge(1, 2);
	    test.addEdge(1, 3);
	    test.addEdge(3, 4);
	    test.addEdge(4, 5);
	    test.addEdge(4, 6);
					
	    assertEquals("", 1, test.indegree(4));
	    assertEquals("", 2, test.outdegree(4));
	    assertEquals("", 5, test.E());
	    assertEquals("", 10, test.V());
	    
	    String ans = "[5, 6]";
	    assertEquals("",ans, test.adj(4).toString());
    }
	
	@Test
	public void testsForCycle(){
		lcaDag cyclic = new lcaDag(20);
		cyclic.addEdge(0, 1);
		cyclic.addEdge(1, 2);
		cyclic.addEdge(2, 0);
		
		//0 is first vertex
		cyclic.findCycle(0);
	
		assertTrue(cyclic.hasCycle());
		
		lcaDag acyclic = new lcaDag(20);
		acyclic.addEdge(0, 1);
		acyclic.addEdge(1, 4);
		acyclic.addEdge(2, 5);
		
		//0 is first vertex
		acyclic.findCycle(0);
		
		//No Cycle,return false
		assertFalse(acyclic.hasCycle());
	}
	
	@Test
	public void testAddEdge(){
		lcaDag testEdge = new lcaDag(5);
		testEdge.addEdge(0, 1);
		
		assertEquals("Testing edge count is 1", 1, testEdge.E());
		
		testEdge.addEdge(1, 2);
		
		assertEquals("Testing edge count is 2", 2, testEdge.E());
	}
	
	@Test
	public void testLCAForNonDAG(){
		lcaDag nonAcyclic = new lcaDag(11);
		
		nonAcyclic.addEdge(0, 1);
		nonAcyclic.addEdge(1, 2);
		nonAcyclic.addEdge(2, 0);
		nonAcyclic.addEdge(1, 3);
		nonAcyclic.addEdge(3, 2);
		nonAcyclic.addEdge(2, 4); 
		nonAcyclic.addEdge(4, 3);
		
		//Should return -1 if graph is not a DAG
		assertEquals("", -1, nonAcyclic.findLCA(2, 3));
		assertEquals("", -1, nonAcyclic.findLCA(3, 4));
		assertEquals("", -1, nonAcyclic.findLCA(1, 2));
		assertEquals("", -1, nonAcyclic.findLCA(0, 3));
		}
	
	@Test
	public void testLCAForNoCommonAncestors(){
		lcaDag lca2 = new lcaDag(11);
		lca2.addEdge(0, 1);
		lca2.addEdge(0, 2);
		lca2.addEdge(1, 2);
		lca2.addEdge(2, 3);
		lca2.addEdge(3, 4);
		lca2.addEdge(1, 5);
		lca2.addEdge(3, 5);
		
		assertEquals("when there is no LCA", 0, lca2.findLCA(3, 1));
		assertEquals("", 2, lca2.findLCA(3, 2));
		assertEquals("", 3, lca2.findLCA(4, 5));
		
		//Check for no common ancestors
		assertEquals("when one node doesn't exist", -1, lca2.findLCA(7, 3));
	}
	
	@Test
	public void testOutDegree(){
		lcaDag testDegree = new lcaDag(5);
		assertEquals("", -1, testDegree.outdegree(8));	
	}
	
	
	@Test(expected=Exception.class)
	public void exceptionTest(){
		//Can't make a directed graph with less than 0 vertices
		lcaDag testException = new lcaDag(-4);
	}	
	
	@Test
	public void testLCAforEmpty() {
		lcaDag lcaEmpty = new lcaDag(9);
		assertEquals("Testing LCA is -1", -1, lcaEmpty.findLCA(0, 2));
	}
	
	@Test
	public void testLCA(){
		lcaDag lca = new lcaDag(10);
		lca.addEdge(0, 1);
		lca.addEdge(1, 2);
		lca.addEdge(1, 3);
		lca.addEdge(2, 5);
		lca.addEdge(3, 4);
		lca.addEdge(4, 6);
		lca.addEdge(5, 6);
		lca.addEdge(6, 8);
		lca.addEdge(5, 7);
		lca.addEdge(7, 8);
		lca.addEdge(8, 9);
					
	    assertEquals("4 and 2", 1, lca.findLCA(2, 4));
		assertEquals("7 and 8", 7, lca.findLCA(8, 7));
		assertEquals("9 and 8", 8, lca.findLCA(9, 8));
		assertEquals("Special case", 3, lca.findLCA(3,3));
	}
			
			
			
}
	


