package com.ip13.cp.repository;

import com.ip13.cp.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
//    @Modifying
//    @Query("update Person p set p.name = :name where p.id = :id")
//    void updateName(@Param("id") Integer id, @Param("name") String name);
}
