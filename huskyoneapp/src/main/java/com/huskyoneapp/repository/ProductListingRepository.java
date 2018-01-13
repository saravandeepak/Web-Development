package com.huskyoneapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huskyoneapp.entity.ProductListing;
import com.huskyoneapp.entity.UserAccount;

@Repository
public interface ProductListingRepository extends CrudRepository<ProductListing, Long> {
	Long countByApproved(String approval);
	List<ProductListing> findByPostedby(UserAccount userAccount);

}
