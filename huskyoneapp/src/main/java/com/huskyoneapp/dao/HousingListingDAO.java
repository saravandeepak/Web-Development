package com.huskyoneapp.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.ProductListing;

@Repository
public class HousingListingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
    public void saveOrUpdate(HousingListing housingListing) {
        sessionFactory.getCurrentSession().saveOrUpdate(housingListing);
    }
	
	@Transactional
    public HousingListing getHouse(Long houseId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HousingListing.class);
        criteria.add(Restrictions.eq("housingListingId", houseId));
        HousingListing housingListing = (HousingListing) criteria.uniqueResult();
        return housingListing;
    }
	
	@Transactional
	public Collection<HousingListing> getHouseListing(String lon, String sub, String room, String bnb, float minPrice, float maxPrice, String searchKey, String orderby){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HousingListing.class, "h");
		
		Collection<String> types = new ArrayList<>();
		
		
		Collection<HousingListing> result ;
		
		if(!lon.equals("")){
			types.add(lon);
		}
		if(!sub.equals("")){
			types.add(sub);
		}	
		if(!room.equals("")){
			types.add(room);
		}
		if(!bnb.equals("")){
			types.add(bnb);
		}
		if(!types.isEmpty())
			criteria.add(Restrictions.in("type", types ));
		if(minPrice!= 0){
			criteria.add(Restrictions.ge("rent", minPrice));
		}
		if(maxPrice!=0){
			criteria.add(Restrictions.le("rent",maxPrice));
		}
		
		if(!searchKey.equals("")){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.ilike("title", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("description", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("type", searchKey, MatchMode.ANYWHERE));
			criteria.createAlias("h.address", "addr");
			disjunction.add(Restrictions.ilike("addr.street", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("addr.city", searchKey, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("addr.state", searchKey, MatchMode.ANYWHERE));
			criteria.add(disjunction);
		}
		
		criteria.add(Restrictions.eq("approved", "approved"));
		
		if(orderby.equals("newest"))
			criteria.addOrder(Order.desc("posteddate"));
		else if(orderby.equals("lowprice"))
			criteria.addOrder(Order.asc("rent"));
		else if(orderby.equals("highprice"))
			criteria.addOrder(Order.desc("rent"));
		
		result = new LinkedHashSet<>(criteria.list());
		
		return result;
		
	}
	
	@Transactional
	public Collection<HousingListing> getHouseListing(String approval){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HousingListing.class);
		criteria.add(Restrictions.eq("approved", approval));
		Collection<HousingListing> houses = new LinkedHashSet<>(criteria.list());
		return houses;
	}
	
	@Transactional
	public String approveHouseListing(Long productId){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HousingListing.class);
		criteria.add(Restrictions.eq("housingListingId", productId));
		HousingListing housingListing =  (HousingListing) criteria.uniqueResult();
		housingListing.setApproved("approved");
		return "Successfully Approved";
	}
	
	@Transactional
	public String rejectHouseListing(Long productId){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(HousingListing.class);
		criteria.add(Restrictions.eq("housingListingId", productId));
		HousingListing housingListing =  (HousingListing) criteria.uniqueResult();
		housingListing.setApproved("rejected");
		return "Successfully Approved";
	}
	
	

}
