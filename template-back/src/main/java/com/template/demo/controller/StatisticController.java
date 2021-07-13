package com.template.demo.controller;

import com.template.demo.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticController {

    private StatisticService statisticService;

    @GetMapping
    public List<Integer> getStatistics(){
        return statisticService.lastTwelveHour();
    }

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }
}
