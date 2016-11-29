import java.util.ArrayList;

public class LinkedListRelated {
	
	public LinkedListRelated() {}
	
	Node head = new Node();
	
	Node tail = null;
	
	/** 链表节点定义（数据，指针） */
	private class Node {
		  int x;
		  
		  Node Next; 
		  
		  Node Before;
		  
		  public Node() {
			  Before = null;
			  
			  Next = null;
		  }
		  
		  public Node(int x) {
			  this.x = x;
			  
			  Before = null;
			  
			  Next = null;
		  }
		}

	/** 反向打印单链表 */
	public void P6() {
    	Node temp = head.Next;
    	ArrayList<Integer> arr = new ArrayList<>(); 
    	while(temp != null) {
		    arr.add(temp.x);
		    temp = temp.Next;
		}
    	System.out.println();
    	for(int i = (arr.size() -1) ; i >= 0 ; i--) {
    		System.out.print(arr.get(i) + " ");
    	}
    }
	
	/**删除单链表的第一个节点 */
	public void P8A() {
		Node temp = head.Next;
		head.Next = temp.Next;
	}
	
	/**在单链表中添加一个节点，使其成为第一个节点 */ 
	public void P8B(Node x) {
		Node temp = head.Next;
		head.Next = x;
		x.Next = temp;
	}
	
	
}
