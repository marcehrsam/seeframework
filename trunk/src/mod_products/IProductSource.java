package mod_products;

import java.util.List;

import model_test.Produkt;

public interface IProductSource {
	public Produkt getProduct();
	public int getCount();
	public List<Produkt> getProducts();
}
