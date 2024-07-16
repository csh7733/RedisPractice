package com.example.redis.practice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConst {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    public static final String leaderboardKey = "leaderboard:" + dateFormat.format(new Date());
}
