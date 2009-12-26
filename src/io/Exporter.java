package io;

import data.Voucher;

public abstract class Exporter {
	
	protected Voucher voucher;
	
	public Exporter(Voucher voucher){
		this.voucher = voucher;
	}
	
	protected boolean modified = true; 
	
	public abstract boolean save();
	
	public boolean isModified(){
		return modified;
	}
	
	public void setModified(boolean modified){
		this.modified = modified;
	}

}
