//2.4.24
//page 331

public class MaxPQWithLinks<Item extends Comparable<Item>>
{
	private Node<Item> root,lastNode;
	private int size,levels=-1;
	
	public MaxPQWithLinks()
	{
	}
	
	public MaxPQWithLinks(Item[] items)
	{
	}
	
	public void insert(Item item)
	{
		if(root==null)
		{
			root=new Node(item);
			lastNode=root;
			levels=0;
		}
		else
		{
			lastNode=new Node(item);
			if(size==1<<levels)	//full tree, add next level;
			{
				levels++;
				Node node=root;
				while(node!=null&&node.getLeftChild()!=null)
					node=node.getLeftChild();
				lastNode=new Node(item);
				node.addChild(lastNode);
			}
			else	//incomplete full tree
			{
				Node node=lastNode;
				while(true)
				{
					Node parent=node.getParent();
					if(parent.canHaveChildren()==true)
					{
						lastNode=new Node(item);
						parent.addChild(lastNode);
						break;
					}
					else
					{
					}
				}
			}
		}
		
		size++;
		
		//swim
	}
	
	public Item max()
	{
	}
	
	public Item removeMax()
	{
	}
	
	public boolean isEmpty()
	{
		return size==0
	}
	
	public int size()
	{
		return size;
	}
	
	class Node<Item extends Comparable<Item>>
	{
		private Item value;
		private Node<Item> leftChild,rightChild,parent;
		
		public Node(Item value)
		{
			this.value=value;
		}
		
		private void setParent(Node node)
		{
			this.parent=node;
		}
		
		public void addChild(Node node) throws InvalidOperationException
		{
			if(leftChild==null)
				leftChild=node;
			else if (rightChild==null)
				rightChild=node;
			else throw new InvalidOperationException("The node has no free children");
			node.setParent(this);
		}
		
		public boolean canHaveChildren()
		{
			return leftChild!=null || rightChild!=null;
		}
		
		public Node getLeftChild()
		{
			return leftChild;
		}
		
		public Node getRightChild()
		{
			return rightChild;
		}
		
		public Node getParent()
		{
			return parent;
		}
	}
}