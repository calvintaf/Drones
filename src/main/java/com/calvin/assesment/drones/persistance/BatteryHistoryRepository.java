package com.calvin.assesment.drones.persistance;

import com.calvin.assesment.drones.domain.BatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryHistoryRepository extends JpaRepository<BatteryHistory, Long> {
}
