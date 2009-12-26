package data;

import io.Exporter;
import io.InvoiceExporter;

public class Invoice extends Voucher{

	public Invoice(String voucherNumber){
		super(voucherNumber);
	}

	@Override
	public Exporter setExporter() {
		return new InvoiceExporter();
	}

}
