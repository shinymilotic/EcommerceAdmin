package vuicungtienganh.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vuicungtienganh.dto.OrderProductForm;
import vuicungtienganh.dto.OrderProductListContainer;
import vuicungtienganh.entity.OrderProduct;
import vuicungtienganh.entity.Product;
import vuicungtienganh.entity.SalesOrder;
import vuicungtienganh.entity.User;
import vuicungtienganh.service.ProductService;
import vuicungtienganh.service.SalesOrderService;
import vuicungtienganh.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SalesOrderService salesOrderService;
	
	@GetMapping("")
	public String showOrders(Model model) {
		List<SalesOrder> salesOrders = salesOrderService.findAll();
		model.addAttribute("orders", salesOrders);
		return "order/orders";
	}
	
	@GetMapping("{id}")
	public String showOrderDetail(@PathVariable("id") int orderId,
			Model model) {
		SalesOrder salesOrder = salesOrderService.findById(orderId);
		model.addAttribute("salesOrder", salesOrder);
		model.addAttribute("user", salesOrder.getCustomer());
		return "order/show-order";
	}
	
	@PostMapping("delete")
	public String deleteOrders(@RequestParam("orderCheckboxes") List<Integer> orderIdList) {
		for(int orderId : orderIdList) {
			salesOrderService.deleteSalesOrder(orderId);
		}
		
		return "redirect:/order";
	}
	
	@GetMapping("/choose-user")
	public String showListUserForm(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "order/list-user";
	}
	
	@PostMapping("/choose-product")
	public String showOrderProduct(@RequestParam(name="userRadioButton", required = false) Integer userId,	
			Model model) {
		List<Product> products = productService.findAll();
		
		if(userId == null) {
			return "redirect:/";
		}
		
		model.addAttribute("user_id", userId);
		model.addAttribute("products", products);
		return "order/list-product";
	}
	@PostMapping("/show")
	public String showSalesOrder(@NotNull @RequestParam("user_id") int userId,
			@ModelAttribute("orderProducts") OrderProductListContainer productList,
			Model model) throws Exception {
		// Get customer
		User user = userService.findById(userId);
		// Get order_products
		List<OrderProductForm> orderProductForms = productList.getOrderProducts();
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		long total = 0;
		for(OrderProductForm op : orderProductForms) {
			System.out.println(op.toString());
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setPrice(op.getPrice());
			orderProduct.setProduct(op.getProduct());
			orderProduct.setQuantity(op.getQuantity());
			total = total + op.getQuantity()*op.getPrice();
			System.out.println(total);
			orderProducts.add(orderProduct);
		}
		// create sales order
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustomer(user);
		salesOrder.setOrderTime(LocalDateTime.now());
		salesOrder.setOrderProducts(orderProducts);
		salesOrder.setTotal(total);
		SalesOrder savedSalesOrder = salesOrderService.save(salesOrder);
		model.addAttribute("salesOrder", savedSalesOrder);
		model.addAttribute("user", user);
		//
		return "order/show-order";
	}
}
