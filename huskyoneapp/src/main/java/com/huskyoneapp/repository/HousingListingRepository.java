package com.huskyoneapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.UserAccount;

@Repository
public interface HousingListingRepository extends CrudRepository<HousingListing, Long> {
	Long countByApproved(String approval);
	List<HousingListing> findByPostedby(UserAccount userAccount);
}
