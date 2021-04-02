package com.lansrod.api.repository;

import com.lansrod.api.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
