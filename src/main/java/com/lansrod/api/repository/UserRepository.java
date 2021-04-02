package com.lansrod.api.repository;

import com.lansrod.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Philemon Globlehi <philemon.globlehi@gmail.com>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByUsername(String username);

    public User findByUsername(String username);

    @Transactional
    public void deleteByUsername(String username);
}
