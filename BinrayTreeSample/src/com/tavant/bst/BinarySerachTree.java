package com.tavant.bst;

public class BinarySerachTree {
	
	public static Node root;
	
	public BinarySerachTree()
	{
		BinarySerachTree.root = null;
	}

	public static void main(String[] args)
	{
		BinarySerachTree bst = new BinarySerachTree();
		bst.insert(5);
		bst.insert(6);
		bst.insert(1);
		bst.insert(4);
		bst.insert(8);
		bst.insert(7);
		bst.insert(2);
		bst.insert(10);
		bst.insert(9);
		bst.display(BinarySerachTree.root);
		System.out.println(bst.find(3));
		System.out.println(bst.find(10));

	}

	private boolean find(int i)
	{
		Node current = root;
		int  count = 0 ;
		while(current != null)
		{
			++count;
			if(current.data == i)
			{
				
				System.out.println( i + " Found");
				System.out.println("found at :" + count);
				return true;
			}
			else if(current.data>i)
			{
				current = current.left;
			}
			else
				current = current.right;
		}
		return false;
		
		
	}

	private void display(Node root) 
	{
		if(root != null)
		{
			display(root.left);
			System.out.println("" + root.data);
			display(root.right);
		}
		
	}

	private void insert(int i) 
	{
		Node newNode = new Node(i);
		if(root == null)
		{
			root = newNode;
			return;
		}
		
		Node current = root;
		Node parent = null;
		
		while(true)
		{
			parent = current;
			if(i<current.data)
			{
				current = current.left;
				if(current == null)
				{
					parent.left= newNode;
					return;
				}
			}
			else
			{
				current = current.right;
				if(current == null)
				{
					parent.right = newNode;
					return;
				}
			}
			
		}
	}

}
