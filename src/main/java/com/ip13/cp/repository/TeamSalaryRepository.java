package com.ip13.cp.repository;

import com.ip13.cp.model.TeamSalary;
import com.ip13.cp.model.TeamSalaryKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TeamSalaryRepository extends CrudRepository<TeamSalary, TeamSalaryKey> {

}
