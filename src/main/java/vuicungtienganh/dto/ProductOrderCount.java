package vuicungtienganh.dto;

public class ProductOrderCount {
	private String productName;
	private int orderCount;
	public ProductOrderCount() {
		super();
	}
	public ProductOrderCount(String productName, int orderCount) {
		super();
		this.productName = productName;
		this.orderCount = orderCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	@Override
	public String toString() {
		return "ProductOrderCount [productName=" + productName + ", orderCount=" + orderCount + "]";
	}
	
	
}
