package vuicungtienganh.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vuicungtienganh.dto.ProductOrderCount;
import vuicungtienganh.service.SalesOrderService;

@Controller
public class HomeController {
	@Autowired
	private SalesOrderService salesOrderService;
	
	@GetMapping("/")
	public String showHome(Model model) throws SQLException {
		List<Object[]> rs = salesOrderService.productOrderCount();
		List<ProductOrderCount> productOrderCount = new ArrayList<ProductOrderCount>();
		for(Object[] row : rs) {
			ProductOrderCount p = new ProductOrderCount(row[0].toString(), Integer.parseInt(row[1].toString()));
			productOrderCount.add(p);
		}
		
		model.addAttribute("productOrderCount", productOrderCount);
		model.addAttribute("earning", salesOrderService.getEarningAmount());
		model.addAttribute("productOrdered", salesOrderService.productOrdered());
		return "index";
	}
	
	
	
}

