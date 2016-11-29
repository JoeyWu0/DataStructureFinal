// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 *
 * 第15至16题
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     * @throws Exception 
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception("Underflow");
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     * @throws Exception 
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception("Underflow");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    public void numberOfNodes() {
    	System.out.println("此二叉树的节点个数为：" + numberOfNodes(root));
    }
    
    public void numberOfLeaves() {
    	System.out.println("此二叉树的树叶个数为：" + numberOfLeaves(root));
    }
    
    public void numberOfFulls() {
    	System.out.println("此二叉树的满节点的个数为：" + numberOfFulls(root));
    }
    
    public void checkBST () {
    	if(checkBST(root))
    	  System.out.println("此二叉树满足查找树的性质。");
    	else 
    	  System.out.println("此二叉树不 满足查找树的性质。");
    }
    
    public void removeLeaves() {
    	root = removeLeaves(root);
    	System.out.println("操作：删除全部树叶。");
    }
    
    public boolean checkSimilar(BinaryNode<AnyType> node1 , BinaryNode<AnyType> node2) {
    	if(node1 == null && node2 == null) {
    		return true;
    	}
    	if(node1 == null || node2 == null) {
    		return false;
    	}
    	else {
    		return checkSimilar(node1.left , node2.left) && checkSimilar(node1.right , node2.right);
    	}
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    @SuppressWarnings("unused")
	private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        @SuppressWarnings("unused")
		BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }

	private int numberOfNodes (BinaryNode<AnyType> node) {
    	int nodes = 0;
    	if(node == null) {
    		return 0;
    	}
    	else {
    		nodes = 1 + numberOfNodes(node.left) + numberOfNodes(node.right);
    	}
    	return nodes;
    }
	
	private int numberOfLeaves(BinaryNode<AnyType> node) {
		int leaves = 0;
		if(node == null) {
			return 0;
		}
		else if(node.left == null && node.right == null) {
			return 1;
					}
		else {
			leaves = numberOfLeaves(node.left) + numberOfLeaves(node.right); 
		}
		return leaves;
	}
	
	
    private int numberOfFulls(BinaryNode<AnyType> root){
        int nodes = 0;
        if(root == null)
            return 0;
        else if(root.left == null && root.right == null)
            return 0;
        else if(root.left == null && root.right != null)
            nodes = numberOfFulls(root.right);
        else if(root.left != null && root.right == null)
            nodes = numberOfFulls(root.left);            
        else
            nodes = 1 + numberOfFulls(root.left) + numberOfFulls(root.right);
        return nodes;
    }
    
    private boolean checkBST(BinaryNode<AnyType> node) {
    	boolean temp = true;
    	if(node == null) {
    		return true;
    	}
    	if(node.left == null && node.right == null) {
    		return true;
    	}
    	else if(node.left != null && node.right ==null) {
    		return node.left.element.compareTo(node.element)<0;
    	}
    	else if(node.left == null && node.right != null) {
    		return node.right.element.compareTo(node.element)>0;
    	}
    	else {
    		temp = (node.left.element.compareTo(node.element)<0) 
    				&& (node.right.element.compareTo(node.element)>0)
    				&& checkBST(node.left) && checkBST(node.right);
    	}
    	return temp;
    }
    
    private BinaryNode<AnyType> removeLeaves(BinaryNode<AnyType> node) {
    	if(node == null) {
    		return null;
    	}
    	else if(node.left == null && node.right == null) {
    		return null;
    	}
    	else {
    		node.left = removeLeaves(node.left);
    		node.right = removeLeaves(node.right);
    	}
    	return node;
    }
      /** The tree root. */
    public BinaryNode<AnyType> root;

    
        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>( );
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(1);
        tree.numberOfNodes();
        tree.numberOfLeaves();
        tree.numberOfFulls();
        tree.checkBST();
        tree.removeLeaves();
        tree.numberOfNodes();
        tree.numberOfFulls();
        
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>( );
        tree2.insert(5);
        tree2.insert(3);
        tree2.insert(7);
        tree2.insert(2);
        tree2.insert(4);
        tree2.insert(6);
        tree2.insert(8);
        tree2.insert(1);
        System.out.println(tree.checkSimilar(tree.root, tree2.root));
    }
}
