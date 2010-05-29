package mod_orders.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class ROOT_OrderTreeActions implements TreeNode{

	private ArrayList<TreeNode> children;
	
	public ROOT_OrderTreeActions(){
		children = new ArrayList<TreeNode>();	
		loadActions();
	}
	
	private void loadActions() {
		//actions zum Baum hinzufügen
		children.add(new ACT_TR_OpenNewOrderForm());
		children.add(new ACT_TR_OpenViewOrderForm());
		children.add(new ACT_TR_OpenChangeOrderForm());
		
	}

	@Override
	public Enumeration<TreeNode> children() {
		return Collections.enumeration(children);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return children.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
	
	public String toString(){
		return "Aufgaben";
	}

}
