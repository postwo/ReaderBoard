package com.example.readerboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankingService {

    private static final String LEADERBOARD_KEY ="leaderBoard";

    @Autowired
    StringRedisTemplate redisTemplate;

    public boolean setUserScore(String userId,int score){
        //sorted를 사용하기 위해 Zset을 사용
        ZSetOperations  zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY,userId,score);

        return true;

    }
    
    //특정 유저에 랭킹을 조회하는 메소드
    public Long getUserRankings(String userId){
        ZSetOperations  zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY,userId);

        return rank;
    }

    //범위기반 조회 메소드
    public List<String> getTopRank(int limit){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY,0,limit-1);
        return new ArrayList<>(rangeSet);
    }
}
