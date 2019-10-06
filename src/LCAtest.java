import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
//-------------------------------------------------------------------------
/**
 *  Test class for Lowest Common Ancestor
 *
 *  @author  Divine Mbunga
 */

@RunWith(JUnit4.class)

public class LCAtest {
 
	@Test
	public void testBasicTree() {
		//Testing a very basic typical tree
		LCA tree = new LCA();
		tree.root = new Node(1);
		tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
        tree.root.right.left = new Node(6); 
        tree.root.right.right = new Node(7); 
        
        assertEquals("LCA(4,5)",2,tree.findLCA(4,5));
        assertEquals("LCA(4,6)",1,tree.findLCA(4,6));
        assertEquals("LCA(3,4)",1,tree.findLCA(3,4));
        assertEquals("LCA(2,4)",2,tree.findLCA(2,4));
	}
	
	@Test
	public void testNullTree(){
		LCA tree = new LCA();
		tree.root = null;
		
		//should return -1 as the root is null- non existent
		assertEquals("root is null",-1,tree.findLCA(4,6));
	}
	
	@Test
	public void testSmallTree(){
		LCA tree = new LCA();
		tree.root = new Node(1);
		tree.root.left = new Node(2); 
        tree.root.right = new Node(3);
        
        assertEquals("LCA(2,3)",1,tree.findLCA(2,3));
	}
	
	/*To add
	 * -left sided tree
	 * -right sided tree
	 * -big tree*/

}
