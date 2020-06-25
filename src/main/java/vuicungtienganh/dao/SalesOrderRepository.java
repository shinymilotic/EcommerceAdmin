package vuicungtienganh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vuicungtienganh.dto.ProductOrderCount;
import vuicungtienganh.entity.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer>{
	@Query("SELECT op.product.productName as productName, SUM(op.quantity) AS orderCount "
					+ "FROM SalesOrder so join so.orderProducts op "
					+ "GROUP BY op.product.productName "
					+ "ORDER BY orderCount DESC")
	public List<Object[]> getProductOrderCount();
	@Query("SELECT SUM(so.total) as Sum "
			+ "FROM SalesOrder so")
	public long getEarning();
	@Query("SELECT SUM(op.quantity) AS orderCount "
			+ "FROM SalesOrder so join so.orderProducts op ")
	public int getProductOrdered();
	
}


