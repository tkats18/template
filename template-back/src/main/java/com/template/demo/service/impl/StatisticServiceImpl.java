package com.template.demo.service.impl;

import com.template.demo.service.StatisticService;
import com.template.demo.storage.entity.StatisticEntity;
import com.template.demo.storage.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    private StatisticRepository repository;

    @Override
    public void addNewSentEmail() {
        repository.save(new StatisticEntity(null, LocalDateTime.now()));
    }

    @Override
    public List<Integer> lastTwelveHour() {
        List<Integer> res= new ArrayList<>();

        for (int i=12; i>0; i--){
            res.add(repository.findAllByTimeAfterAndTimeBefore(
                    LocalDateTime.now().minus(i, ChronoUnit.HOURS),
                    LocalDateTime.now().minus(i-1, ChronoUnit.HOURS)
            ).size());

        }


        return res;
    }

    @Autowired
    public void setRepository(StatisticRepository repository) {
        this.repository = repository;
    }
}
