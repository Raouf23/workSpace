package com.tavant.linkedlist;



public class linkedListImp {
	
	public static Node head;
	public static Node start;
	
	public linkedListImp()
	{
		linkedListImp.head = null;
	}
	
	public static void main(String[] args)
	{
		linkedListImp llist = new linkedListImp();
		llist.insert(10);
		llist.insert(2);
		llist.insert(1);
		llist.insert(12);
		llist.insert(15);
		llist.insert(23);
		llist.display();
		head = llist.reverse(head);
		System.out.println();
		llist.display();
	}
	
	
	Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}

	private void display()
	{
		  Node n = head;
	        while (n != null)
	        {
	            System.out.print(n.data+" ");
	            n = n.next;
	        }
		
	}

	private void insert(int i)
	{
		Node nptr = new Node(i);
		if(head== null)
		{
			nptr.data = i;
			nptr.next = head;
			head =nptr;
			start = head;
		}
		else		
		{
			while(head.next != null)
				head = head.next;
			nptr.data = i;
			nptr.next = head.next;
			head.next = nptr;
		}
		head = start;
	}
	
	

}
