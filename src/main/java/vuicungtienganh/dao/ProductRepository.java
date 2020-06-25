package vuicungtienganh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vuicungtienganh.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product saveAndFlush(Product product);
	public List<Product> findAll();
}
