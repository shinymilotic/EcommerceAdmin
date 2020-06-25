package vuicungtienganh.dto;

import java.util.List;

import vuicungtienganh.entity.OrderProduct;
import vuicungtienganh.entity.Product;

public class OrderProductListContainer {
	private List<OrderProductForm> orderProducts;

	public OrderProductListContainer(List<OrderProductForm> orderProducts) {
		super();
		this.orderProducts = orderProducts;
	}

	public List<OrderProductForm> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductForm> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "OrderProductListContainer [orderProducts=" + orderProducts + "]";
	}

	
	
}
