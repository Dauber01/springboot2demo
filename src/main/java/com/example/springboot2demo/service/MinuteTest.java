package com.example.springboot2demo.service;

/**
 * @author Lucifer
 * @do 小时测试
 * @date 2018/08/11 9:20
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 根据权重平均分配的算法
 *
 * @author Lucifer
 * @date 2018／08／10 23:11
 */
public class MinuteTest {

    public static void main(String[] args){
        int num = 12;
        int power = 6;
        int now = 0;
        List<Integer> list = new ArrayList<Integer>();
        int totlePower = 8*60 + 8*60*power + 8*60;
        int everyPower = totlePower % num == 0 ? totlePower / num : totlePower /num +1;
        for (int i = 0; i < num; i++){
            int currentHour = 0;
            int currentPower = 0;
            if (now < 8 *60){
                if (everyPower > (8*60 - now)){
                    currentPower = 8*60 - now;
                    currentHour = 8*60 - now;
                    now = 8*60;
                }else{
                    list.add(everyPower);
                    now = now + everyPower;
                    continue;
                }
            }
            if (now >= 8*60 && now < 16*60){
                int pnu = (everyPower - currentPower)%6==0?(everyPower - currentPower)/6:(everyPower - currentPower)/6+1;
                if (16*60 - now - pnu < 0){
                    currentPower = currentPower + (16*60 - now) * power;
                    currentHour = currentHour + (16*60 - now);
                    now = 16*60;
                }else {
                    now = now + pnu;
                    list.add(currentHour + pnu);
                    continue;
                }
            }
            if (now >= 16*60 && now < 24*60){
                if (everyPower - currentPower < 24*60 - now){
                    now = now + (everyPower - currentPower);
                    currentHour = currentHour + (everyPower - currentPower);
                    list.add(currentHour);
                }else{
                    currentHour = currentHour + (24*60 - now);
                    now = 24*60;
                    list.add(currentHour);
                    continue;
                }
            }
        }
        System.out.println(list);
    }

}

