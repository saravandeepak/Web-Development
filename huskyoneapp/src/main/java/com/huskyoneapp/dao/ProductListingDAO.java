package com.huskyoneapp.dao;


import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huskyoneapp.entity.BookListing;
import com.huskyoneapp.entity.ComputerListing;
import com.huskyoneapp.entity.ElectronicsListing;
import com.huskyoneapp.entity.FurnitureListing;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.ProductListing;

@Repository
public class ProductListingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveBook(BookListing bookListing){
		sessionFactory.getCurrentSession().save(bookListing);
	}
	
	@Transactional
	public void saveComputer(ComputerListing computerListing){
		sessionFactory.getCurrentSession().save(computerListing);
	}
	
	@Transactional
	public void saveElectronic(ElectronicsListing electronicsListing){
		sessionFactory.getCurrentSession().save(electronicsListing);
	}
	
	@Transactional
	public void saveFurniture(FurnitureListing furnitureListing){
		sessionFactory.getCurrentSession().save(furnitureListing);
	}
	
	@Transactional
	public ProductListing getProduct(Long productId){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductListing.class);
		criteria.add(Restrictions.eq("productListingId", productId));
		ProductListing productListing = (ProductListing) criteria.uniqueResult();
		return productListing;
	}
	
	@Transactional
	public Collection<ProductListing> getProductsListing(String type, float minPrice, float maxPrice, Collection<String> condition, String searchKey, String orderby){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductListing.class, "p");
		if(type!=null){
			if(type.equals("book")){
				criteria = sessionFactory.getCurrentSession().createCriteria(BookListing.class);
				if(maxPrice!=0){
					criteria.add(Restrictions.le("price",maxPrice));
				}
				if(!condition.isEmpty()){
					criteria.add(Restrictions.in("productCondition", condition));
				}
				criteria.add(Restrictions.eq("approved", "approved"));
				return new LinkedHashSet<>(criteria.list());
			}
			else if(type.equals("computer")){
				criteria = sessionFactory.getCurrentSession().createCriteria(ComputerListing.class);
				if(maxPrice!=0){
					criteria.add(Restrictions.le("price",maxPrice));
				}
				if(!condition.isEmpty()){
					criteria.add(Restrictions.in("productCondition", condition));
				}
				criteria.add(Restrictions.eq("approved", "approved"));
				return new LinkedHashSet<>(criteria.list());
			}
			else if(type.equals("electronic")){
				criteria = sessionFactory.getCurrentSession().createCriteria(ElectronicsListing.class);
				if(maxPrice!=0){
					criteria.add(Restrictions.le("price",maxPrice));
				}
				if(!condition.isEmpty()){
					criteria.add(Restrictions.in("productCondition", condition));
				}
				criteria.add(Restrictions.eq("approved", "approved"));
				return new LinkedHashSet<>(criteria.list());
			}
			else if(type.equals("furniture")){
				criteria = sessionFactory.getCurrentSession().createCriteria(FurnitureListing.class);
				if(maxPrice!=0){
					criteria.add(Restrictions.le("price",maxPrice));
				}
				if(!condition.isEmpty()){
					criteria.add(Restrictions.in("productCondition", condition));
				}
				criteria.add(Restrictions.eq("approved", "approved"));
				return new LinkedHashSet<>(criteria.list());
			}
		}
		if(minPrice!= 0){
			criteria.add(Restrictions.ge("price", minPrice));
		}
		if(maxPrice!=0){
			criteria.add(Restrictions.le("price",maxPrice));
		}
		if(!condition.isEmpty()){
			criteria.add(Restrictions.in("productCondition", condition));
		}
		
		if(!searchKey.equals("")){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.ilike("title", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("description", searchKey, MatchMode.ANYWHERE));
			criteria.createAlias("p.address", "addr");
			disjunction.add(Restrictions.ilike("addr.street", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("addr.city", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("addr.state", searchKey, MatchMode.ANYWHERE));
			criteria.add(disjunction);
		}
		
		if(orderby.equals("newest"))
			criteria.addOrder(Order.desc("postedDate"));
		else if(orderby.equals("lowprice"))
			criteria.addOrder(Order.asc("price"));
		else if(orderby.equals("highprice"))
			criteria.addOrder(Order.desc("price"));
		
		criteria.add(Restrictions.eq("approved", "approved"));
		
		Collection<ProductListing> productListing = new LinkedHashSet<>(criteria.list());
		return productListing;
	}
	
	@Transactional
	public Collection<ProductListing> getProductsListing(String approval){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductListing.class);
		criteria.add(Restrictions.eq("approved", approval));
		Collection<ProductListing> products = new LinkedHashSet<>(criteria.list());
		return products;
	}
	
	@Transactional
	public String approveProductListing(Long productId){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductListing.class);
		criteria.add(Restrictions.eq("productListingId", productId));
		ProductListing productListing = (ProductListing) criteria.uniqueResult();
		productListing.setApproved("approved");
		return "Successfully Approved";
	}
	
	@Transactional
	public String rejectProductListing(Long productId){
	
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductListing.class);
		criteria.add(Restrictions.eq("productListingId", productId));
		ProductListing productListing = (ProductListing) criteria.uniqueResult();
		productListing.setApproved("rejected");
		return "Successfully Approved";
	}
	
	

}
