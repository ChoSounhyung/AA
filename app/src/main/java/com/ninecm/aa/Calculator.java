package com.ninecm.aa;

import android.util.Log;

import java.util.Calendar;
import java.util.List;

/* 필요한 변환 혹은 계산을 담은 클래스 */
public class Calculator {
    private Calendar calendar;

    // Constructor
    public Calculator(int year, int month, int day) {
        //날짜 설정
        calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
    }

    // 문자열 yyyymmdd로부터 연도를 가져옴
    public static int getYear(String date) {
        int year;
        year = Integer.parseInt(date.substring(0, 4));

        return year;
    }

    // 문자열 yyyymmdd로부터 달을 가져옴
    public static int getMonth(String date) {
        int month;
        month = Integer.parseInt(date.substring(4, 6));

        return month;
    }

    // 문자열 yyyymmdd로부터 날짜를 가져옴
    public static int getDay(String date) {
        int day;
        day = Integer.parseInt(date.substring(6, 8));

        return day;
    }

    public String getDayOfWeek() {
        String[] koreanList = {"일", "월", "화", "수", "목", "금", "토"};
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = koreanList[dayNum-1];
        return dayOfWeek;
    }

   // 날짜나 달이 1글자인지 2글자인지 판별해 포맷을 맞춤
    public static String oneToTwo(int notKnow) {
        String result = String.valueOf(notKnow);
        if (result.length() == 1) {
            result = "0" + result;
        }

        return result;
    }

    // D-Day 계산
    public int calDday() {
        // 휴대폰의 날짜를 기준으로 calendar 생성
        Calendar today = Calendar.getInstance();

        // 86400000 == 1일
        long endDate = calendar.getTimeInMillis() / 86400000;
        long currDate = today.getTimeInMillis() / 86400000;

        long count = endDate - currDate;
        return (int) count;
    }

    // Emergency 계산
    public static int getEmergIndex(List<Cosmetic> cosmetics) {
        int emergIndex = -1;
        if (cosmetics.size() != 0) {
            int[] dCountList = new int[cosmetics.size()];
            for (int i = 0; i < cosmetics.size(); i++) {
                Calculator calculator = Calculator.setCalculator(cosmetics, i);

                // D-Day 계산
                dCountList[i] = calculator.calDday();
            }

            int min = dCountList[0];
            for (int j = 0; j < dCountList.length; j++) {
                if (min > dCountList[j]) {
                    min = dCountList[j];
                }
            }

            for (int k = 0; k < dCountList.length; k++) {
                if (min == dCountList[k]) {
                    emergIndex = k;
                }
            }
        }

        return emergIndex;
    }

    public static Calculator setCalculator(List<Cosmetic> cosmetics, int index) {
        int year = Calculator.getYear(cosmetics.get(index).getEndDay());
        int month = Calculator.getMonth(cosmetics.get(index).getEndDay());
        int day = Calculator.getDay(cosmetics.get(index).getEndDay());
        Calculator calculator = new Calculator(year, month, day);

        return calculator;
    }

    // D-Day 계산 결과에 따른 문자열 반환
    public String stringDday(int dDay) {
        String result;
        if (dDay == 0) {
            result = "유통기한이 오늘까지입니다.";
        } else if (dDay > 0) {
            result = "D - "+String.valueOf(dDay);
        } else {
            result = "유통기한이 "+Math.abs(dDay)+"일 지났습니다.";
        }

        return result;
    }
}