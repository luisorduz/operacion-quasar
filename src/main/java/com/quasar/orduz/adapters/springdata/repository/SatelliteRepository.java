package com.quasar.orduz.adapters.springdata.repository;

import com.quasar.orduz.adapters.springdata.entity.SatelliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, Long> {

    @Transactional
    @Modifying
    @Query("update SatelliteEntity s set s.distance = ?1, s.message = ?2 where s.name = ?3")
    int updateDistanceAndMessageByName(double distance, String message, String name);

    SatelliteEntity findByName(String name);

}
