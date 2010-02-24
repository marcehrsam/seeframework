package mod_products;

import java.util.List;

import model_test.PGroup;
import model_test.Produkt;

public interface IProductSource {
	public Produkt getProduct();
	public int getCount();
	public List<PGroup> getProducts();
}
