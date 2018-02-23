/**
 * 
 */
package org.smile921.app.ember.repository;

import java.util.List;

import org.smile921.app.ember.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Administrator
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {

	public static final String FIND_SEX = "select DISTINCT sex from User u";

	@Query(FIND_SEX)
	List<User> findSex();

	Page<User> findAll(Pageable pageable);

	Page<User> findBySexAndEmailContains(String sexName, String emailName, Pageable pageable);

	Page<User> findBySex(String sexName, Pageable pageable);

	User findById(Long id);
}
