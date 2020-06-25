package vuicungtienganh.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vuicungtienganh.dao.CategoryRepository;
import vuicungtienganh.entity.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public List<Category> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	public Category getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return categoryRepository.getOne(categoryId);
	}
}
