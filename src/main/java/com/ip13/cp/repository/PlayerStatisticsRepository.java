package com.ip13.cp.repository;

import com.ip13.cp.model.PlayersStatistics;
import com.ip13.cp.model.PlayerStatisticsKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PlayerStatisticsRepository extends CrudRepository<PlayersStatistics, PlayerStatisticsKey> {

}
