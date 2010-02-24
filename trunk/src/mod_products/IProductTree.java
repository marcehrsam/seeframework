package mod_products;

import javax.swing.tree.MutableTreeNode;

public interface IProductTree extends MutableTreeNode{
	
	public String getName();
	public boolean addTreeItem(IProductTree item);
	

}
