package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ElevatorService {

    private static double BASE_PRESSURE = 120.12;         // 기준 압력
    private static double PRESSURE_PER_FLOOR = 0.12;      // 층당 기압 변화 (임의로 설정)

    public boolean pressureInit(double basePressure, double pressureFloor) {
        try {
            // 넘어온 값들을 입력
            BASE_PRESSURE = basePressure;
            PRESSURE_PER_FLOOR = pressureFloor;
            return true;
        } catch (Exception e) {
            // 오류 발생 시 false return
            return false;
        }
    }

    public int calculateFloorFromPressure(double currentPressure) {
        // 기압 차이를 이용하여 층수를 계산
        double pressureDifference = BASE_PRESSURE - currentPressure;
        return (int) (pressureDifference / PRESSURE_PER_FLOOR) + 1;
    }
}
