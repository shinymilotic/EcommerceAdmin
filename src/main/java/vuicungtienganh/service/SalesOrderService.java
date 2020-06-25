package vuicungtienganh.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vuicungtienganh.dao.SalesOrderRepository;
import vuicungtienganh.dto.ProductOrderCount;
import vuicungtienganh.entity.SalesOrder;

@Service
public class SalesOrderService {
	@Autowired
	private SalesOrderRepository salesOrderDao;
	
	@Transactional
	public List<Object[]> productOrderCount() {
		return salesOrderDao.getProductOrderCount();
	}
	@Transactional
	public long getEarningAmount() {
		return salesOrderDao.getEarning();
	}
	@Transactional
	public int productOrdered() {
		return salesOrderDao.getProductOrdered();
	}

	@Transactional
	public SalesOrder save(SalesOrder salesOrder) {
		return salesOrderDao.saveAndFlush(salesOrder);
	}
	
	@Transactional
	public List<SalesOrder> findAll() {
		return salesOrderDao.findAll();
	}
	
	@Transactional
	public SalesOrder findById(int id) {
		return salesOrderDao.getOne(id);
	}
	
	@Transactional
	public void deleteSalesOrder(int id) {
		salesOrderDao.deleteById(id);
	}
}
