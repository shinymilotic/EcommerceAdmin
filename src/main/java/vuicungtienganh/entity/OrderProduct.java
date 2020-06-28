	package vuicungtienganh.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_PRODUCTS")
public class OrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(targetEntity = Product.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price")
	private long price;
	
	public OrderProduct() {
		super();
	}

	@Override
	public String toString() {
		return "OrderProduct [id=" + id + "]";
	}

	public OrderProduct(int id, Product product, int quantity, long price) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	
}
