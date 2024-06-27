package com.example.readerboard.controller;

import com.example.readerboard.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/setScore")
    public Boolean setScore(String userId,int score){

        return rankingService.setUserScore(userId,score);
    }


    @GetMapping("/getRank")
    public Long getUserRank(String userId){
    return rankingService.getUserRankings(userId);
    }

    @GetMapping("/getTopRanks")
    public List<String> getTopRank(){
        return rankingService.getTopRank(3);
    }

}
