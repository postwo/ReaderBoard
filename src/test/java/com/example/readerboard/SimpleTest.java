package com.example.readerboard;

import com.example.readerboard.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class SimpleTest {

    @Autowired
    RankingService rankingService;

    @Test
    void getRanks() {
        rankingService.getTopRank(1);  //100ms


        //1
        Instant before = Instant.now();
        Long userRank = rankingService.getUserRankings("user_100");
        Duration elapsed = Duration.between(before,Instant.now());

        System.out.println(String.format("Rank(%d) - Took %d ms",userRank,elapsed.getNano()/1000000));

        //2
        before = Instant.now();
        List<String> toprankers = rankingService.getTopRank(10);
        elapsed =Duration.between(before,Instant.now());

        System.out.println(String.format("Rank - Took %d ms",elapsed.getNano()/1000000));
    }

    @Test
    void insertScore() {
        for (int i=0;i<1000000; i++){
            int score = (int)(Math.random()*1000000); //0~9999999
            String userId = "user"+i;
            rankingService.setUserScore(userId,score);
        }
    }

    //값을 추가하고 정렬을 했을때 얼마나 거리는지 테스트
    @Test
    void inMemorySorftPerFormance(){
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<1000000; i++){
            int score = (int)(Math.random()*1000000); //0~9999999
            list.add(score);
        }

        Instant before = Instant.now();
        Collections.sort(list);
        Duration elapsed = Duration.between(before,Instant.now());
        System.out.println((elapsed.getNano()/1000000)+"ms");
    }
 }
