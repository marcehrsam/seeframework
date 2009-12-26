package data;

import io.Exporter;

import java.util.ArrayList;
import java.util.List;

public abstract class Voucher {

	private List<Voucher> vouchers = null;
	
	private String voucherNumber;
	
	protected Exporter exporter;
	
	public Voucher(String voucherNumber){
		vouchers = new ArrayList<Voucher>();
		this.voucherNumber = voucherNumber;
		exporter = setExporter();
	}
	
	public abstract Exporter setExporter();
	
	public String getVoucherNumber(){
		return voucherNumber;
	}
	public void setVoucherNumber(String number){
		this.voucherNumber = number;
	}
	
	public boolean addVoucher(Voucher voucher){
		return vouchers.add(voucher);
	}
	public boolean removeVoucher(Voucher voucher){
		return vouchers.remove(voucher);
	}
	public List<Voucher> getVouchers(){
		return vouchers;
	}
	
	public String toString(){
		return voucherNumber;
	}
	
	public void export(){
		if(exporter!=null)
			exporter.export();
	}
	
}
