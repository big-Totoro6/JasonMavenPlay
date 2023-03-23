package com.jason.example.dao;

import com.jason.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(
            value = """
      select
      	case
      		when count(*) >0 then 'true'
      		else 'false'
      	end
      from
      	customer s
      where
      	s.email = ?1
      """,
            nativeQuery = true
    )
    Boolean selectExistsEmail(String email);
}
