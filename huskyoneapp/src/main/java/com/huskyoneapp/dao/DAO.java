package com.huskyoneapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.huskyoneapp.entity.Address;
import com.huskyoneapp.entity.BookListing;
import com.huskyoneapp.entity.ComputerListing;
import com.huskyoneapp.entity.ElectronicsListing;
import com.huskyoneapp.entity.FurnitureListing;
import com.huskyoneapp.entity.HousingImages;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.Images;
import com.huskyoneapp.entity.JobListing;
import com.huskyoneapp.entity.ProductImages;
import com.huskyoneapp.entity.ProductListing;
import com.huskyoneapp.entity.UserAccount;
import com.huskyoneapp.entity.UserRoles;

@Configuration
@EnableTransactionManagement
public class DAO {
	
	@Autowired
    private Environment env;
	
	
	@Bean(name="dataSource", destroyMethod = "")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.data-username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.data-password"));
	    return dataSource;
	}
	
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());
	    sessionBuilder.addAnnotatedClasses(UserAccount.class, UserRoles.class, HousingListing.class, Address.class, BookListing.class, ComputerListing.class, ElectronicsListing.class, FurnitureListing.class, HousingImages.class, Images.class, JobListing.class, ProductImages.class, ProductListing.class);

	    return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager() {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            getSessionFactory());
	    return transactionManager;
	}
	
	@Autowired
	@Bean(name = "userAccountDao")
	public UserAccountDAO getUserDao() {
	    return new UserAccountDAO();
	}

}
