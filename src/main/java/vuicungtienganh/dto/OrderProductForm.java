package vuicungtienganh.dto;

import vuicungtienganh.entity.Product;

public class OrderProductForm {
	private int id;
	private String productName;
	private int price;
	private int stock;
	private int quantity;
	private String image;
	
	
	public OrderProductForm() {
		super();
	}


	public OrderProductForm(int id, String productName, int price, int stock, int quantity, String image) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.quantity = quantity;
		this.image = image;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	public Product getProduct() {
		Product product = new Product();
		product.setId(id);
		product.setProductName(productName);
		product.setImage(image);
		product.setPrice(price);
		product.setStock(stock);
		
		return product;
	}

	@Override
	public String toString() {
		return "OrderProductForm [id=" + id + ", productName=" + productName + ", price=" + price + ", stock=" + stock
				+ ", quantity=" + quantity + ", image=" + image + "]";
	}
	
}
