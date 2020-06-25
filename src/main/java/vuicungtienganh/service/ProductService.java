package vuicungtienganh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vuicungtienganh.dao.ProductRepository;
import vuicungtienganh.entity.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productDAO;
	
	@Transactional
	public List<Product> findAll() {
		return productDAO.findAll();
	}
	@Transactional
	public void save(Product product) {
		productDAO.saveAndFlush(product);
	}
	@Transactional
	public Product getProductById(int id) {
		return productDAO.getOne(id);
	}
	@Transactional
	public void deleteProduct(int id) {
		 productDAO.deleteById(id);
	}
}
