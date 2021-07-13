package com.template.demo.storage.repository;

import com.template.demo.storage.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity,Integer> {

    List<StatisticEntity> findAllByTimeAfterAndTimeBefore(LocalDateTime after,LocalDateTime before);

}
