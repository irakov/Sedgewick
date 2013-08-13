//2.4.24
//page 331

import java.util.NoSuchElementException;

public class MaxPQWithLinks<Item extends Comparable<Item>>
{
	private Node<Item> root,lastNode;
	private int size,levels;
	
	public MaxPQWithLinks()
	{
	}
	
	public void insert(Item item) throws InvalidOperationException
	{
		if(root==null)
		{
			root=new Node<Item>(item);
			lastNode=root;
			levels=1;
		}
		else
		{
			if(size==(1<<levels)-1)	//full tree, add next level;
			{
				levels++;
				lastNode=new Node<Item>(item);
				Node<Item> node=root;
				while(node!=null&&node.getLeftChild()!=null)
					node=node.getLeftChild();
				node.addChild(lastNode);
			}
			else	//incomplete full tree
			{
				Node<Item> newLastNode=new Node<Item>(item);
				Node<Item> node=lastNode;
				
				while(true)
				{
					Node<Item> parent=node.getParent();
					if(parent.getLeftChild()==node)
					{                     						if(parent.getRightChild()!=null)
						{
							parent=parent.getRightChild();
							while(parent.getLeftChild()!=null)
								parent=parent.getLeftChild();
						}
						
						parent.addChild(newLastNode);
						lastNode=newLastNode;
						break;
					}
					else
						node=parent;
				}
			}
			//swim(lastNode);
		}
		
		size++;
	}
	
	public Item max()
	{
		if(isEmpty())
			throw new NoSuchElementException();
			
		return root.getValue();
	}
	
	public Item removeMax() throws InvalidOperationException
	{
		if(isEmpty())
			throw new NoSuchElementException();
		Item max=root.getValue();
		if(lastNode==root)
		{	
			root=null;
			levels--;;
			lastNode=null;
		}
		else
		{
			root.setValue(lastNode.getValue());
			
			if(size==(1<<levels))
			{
				levels--;
				lastNode.getParent().removeLeftChild();
				Node<Item> node=root;
				while(node.getRightChild()!=null)
					node=node.getRightChild();
				lastNode=node;
			}
			else
			{
				Node<Item> node=lastNode;
				
				while(true)
				{
					Node<Item> parent=node.getParent();
					if(parent.getRightChild()==node)
					{                     
						parent=parent.getLeftChild();
						while(parent.getRightChild()!=null)
							parent=parent.getRightChild();
																		
						if(lastNode==lastNode.getParent().getLeftChild())
							lastNode.getParent().removeLeftChild();
						else
							lastNode.getParent().removeRightChild();
						lastNode=parent;
						break;
					}
					else
						node=parent;
				}
			}
		}
			
		//sink(root);
		size--;
		
		return max;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int size()
	{
		return size;
	}
	
	private void swim(Node<Item> node) throws InvalidOperationException
	{
		while(node!=root&&node.getParent().getValue().compareTo(node.getValue())>0)
		{
			Node<Item> parentNode=node.getParent();
			Node<Item> parentParent=parentNode.getParent();
			
			Node<Item> leftChild=null;
			if(node.getLeftChild()!=null)
				leftChild=node.removeLeftChild();
				
			Node<Item> rightChild=null;
			if(node.getRightChild()!=null)
				rightChild=node.removeRightChild();
			
			Node<Item> siblingNode=null;
			boolean nodeIsLeft=false;
			if(node==parentNode.getLeftChild())
			{
				node=parentNode.removeLeftChild();
				nodeIsLeft=true;
				if(parentNode.getRightChild()!=null)
					siblingNode=parentNode.removeRightChild();
			}
			else
			{
				node=parentNode.removeRightChild();
				if(parentNode.getLeftChild()!=null)
					siblingNode=parentNode.removeLeftChild();
			}
			
			if(parentParent==null)
				root=node;
			else
			{
				if(parentParent.getLeftChild()==parentNode)
					parentNode=parentParent.removeLeftChild();
				else
					parentNode=parentParent.removeRightChild();
					
				parentParent.addChild(node);
			}
			
			if(nodeIsLeft)
			{
				node.addChild(parentNode);
				if(siblingNode!=null)
					node.addChild(siblingNode);
			}
			else
			{
				node.addChild(siblingNode);
				node.addChild(parentNode);
			}
			
			if(leftChild!=null)
				parentNode.addChild(leftChild);
			if(rightChild!=null)
				parentNode.addChild(rightChild);
		}
	}
	
	private void sink(Node<Item> node) throws InvalidOperationException
	{
		while(node.getLeftChild()!=null)
		{
			boolean isLeftChildGreater=true;
			if(node.getRightChild()!=null)
				if(node.getRightChild().getValue().compareTo(node.getLeftChild().getValue())>0)
					isLeftChildGreater=false;
					
			if(isLeftChildGreater)
				if(node.getLeftChild().getValue().compareTo(node.getValue())>0)
				{
					Item tempValue=node.getValue();
					node.setValue(node.getLeftChild().getValue());
					node.getLeftChild().setValue(tempValue);
				}
				else break;
			else
			{
				if(node.getRightChild().getValue().compareTo(node.getValue())>0)
				{
					Item tempValue=node.getValue();
					node.setValue(node.getRightChild().getValue());
					node.getRightChild().setValue(tempValue);
				}
				else break;
			}
		}
	}
	
	class Node<Item extends Comparable<Item>>
	{
		private Item value;
		private Node<Item> leftChild,rightChild,parent;
		
		public Node(Item value)
		{
			this.value=value;
		}
		
		private void setParent(Node<Item> node)
		{
			this.parent=node;
		}
		
		private void removeParent()
		{
			this.parent=null;
		}
		
		public void addChild(Node<Item> node) throws InvalidOperationException
		{
			if(leftChild==null)
				leftChild=node;
			else if (rightChild==null)
				rightChild=node;
			else throw new InvalidOperationException("The node has no free children");
			node.setParent(this);
		}
		
		public Node<Item> removeLeftChild() throws NoSuchElementException
		{
			if(leftChild==null)
				throw new NoSuchElementException();
				
			leftChild.removeParent();
			Node<Item> result=leftChild;
			leftChild=rightChild;
			rightChild=null;
			
			return result;
		}
		
		public Node<Item> removeRightChild() throws NoSuchElementException
		{
			if(rightChild==null)
				throw new NoSuchElementException();
				
			rightChild.removeParent();
			Node<Item> result=rightChild;
			rightChild=null;
			
			return result;
		}
		
		public Node<Item> getLeftChild()
		{
			return leftChild;
		}
		
		public Node<Item> getRightChild()
		{
			return rightChild;
		}
		
		public Node<Item> getParent()
		{
			return parent;
		}
		
		public Item getValue()
		{
			return value;
		}
		
		public void setValue(Item value)
		{
			this.value=value;
		}
	}
	
	public static void main(String[] args) throws InvalidOperationException
	{
        MaxPQWithLinks<String> pq = new MaxPQWithLinks<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.removeMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}