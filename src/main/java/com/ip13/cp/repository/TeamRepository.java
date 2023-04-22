package com.ip13.cp.repository;

import com.ip13.cp.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

}
