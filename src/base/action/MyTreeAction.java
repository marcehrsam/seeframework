package base.action;

import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.tree.TreeNode;

public abstract class MyTreeAction extends AbstractAction implements TreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4864314388053681814L;
	
	protected String name = "unbenannt";

	@Override
	public Enumeration<MyTreeAction> children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
	
	public String toString(){
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}

}
