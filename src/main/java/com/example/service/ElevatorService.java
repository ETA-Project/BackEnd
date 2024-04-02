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

    public double calculateHeight(double P, double T) {
        // 상수 정의
        double P0 = 100910;     // 해수면의 평균 기압 (파스칼)
        double R = 287.05;      // 특정 기체 상수 (J/kg·K)
        double g = 9.80665;     // 중력 가속도 (m/s^2)

        // 바로 메트릭 공식을 사용한 높이 계산
        double height = (R * T / g) * Math.log(P0 / P);

        return height;
    }
}
