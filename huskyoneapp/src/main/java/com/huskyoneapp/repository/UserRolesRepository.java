package com.huskyoneapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huskyoneapp.entity.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
	
	@Query("select a.role from UserRoles a, UserAccount b where b.userName=?1 and a.userId = b.userId")
	public List<String> findRoleByUserName(String username);

}
