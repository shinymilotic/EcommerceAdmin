package vuicungtienganh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import vuicungtienganh.entity.Category;
import vuicungtienganh.entity.Product;
import vuicungtienganh.service.CategoryService;
import vuicungtienganh.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public String showProduct(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		
		return "product/products";
	}
	
	@GetMapping("/{id}")
	public String showSpecificProduct(@PathVariable("id") int id,
			Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		return "product/product";
	}
	@GetMapping("/insert")
	public String showInsertProductForm(Model model) {
		Product newProduct = new Product();
		List<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("newProduct", newProduct);
		model.addAttribute("categories" , categories);
		return "product/insert";
	}
	
	@PostMapping("/processInsertProductForm")
	public String processInsertProductForm(@ModelAttribute("newProduct") Product product,
			@RequestParam List<Integer> categories,
			BindingResult theBindingResult, 
			Model theModel) {
		List<Category> categories1 = new ArrayList<Category>();
		
		for(int categoryId : categories) {
			Category category = categoryService.getCategoryById(categoryId);
			categories1.add(category);
		}
		product.setCategories(categories1);
		productService.save(product);
		
		return "redirect:/product";
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") int id, Model model) {
		Product product = productService.getProductById(id);
		List<Category> categoryProduct = product.getCategories();
		List<Category> categories = categoryService.getAllCategories();
		Map<Category, Boolean> checkedCategories = new HashMap<Category, Boolean>();
				
		for(Category category : categoryProduct) {
			checkedCategories.put(category, true);
		}
		
		for(Category category : categories) {
			if(!checkedCategories.containsKey(category)) {
				checkedCategories.put(category, false);
			}
		}
		
		model.addAttribute("product", product);
		model.addAttribute("categoryMap", checkedCategories);
		return "product/edit";
	}
	@PostMapping("/processEditProductForm")
	public String processEditProductForm(@ModelAttribute Product product,
			@RequestParam("category") List<Integer> categoryIds,
			BindingResult theBindingResult, 
			Model model) {
		List<Category> categoriesOfProduct = new ArrayList<Category>();
		
		for(int categoryId : categoryIds) {
			Category category = categoryService.getCategoryById(categoryId);
			categoriesOfProduct.add(category);
		}
		
		product.setCategories(categoriesOfProduct);
		productService.save(product);
		
		return "redirect:/product";
	}
	@PostMapping("/delete")
	public String deleteProduct(@RequestParam("productCheckboxes") List<Integer> ids) {
		for(int id : ids) {
			productService.deleteProduct(id);
		}
		
		return "redirect:/product";
	}
}
