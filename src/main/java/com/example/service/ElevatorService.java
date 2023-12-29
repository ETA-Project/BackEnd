package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ElevatorService {

    private static final double BASE_PRESSURE = 120.12;         // 기준 압력
    private static final double PRESSURE_PER_FLOOR = 0.12;      // 층당 기압 변화 (임의로 설정)

    public int calculateFloorFromPressure(double currentPressure) {
        // 기압 차이를 이용하여 층수를 계산
        double pressureDifference = BASE_PRESSURE - currentPressure;
        return (int) (pressureDifference / PRESSURE_PER_FLOOR);
    }
}
