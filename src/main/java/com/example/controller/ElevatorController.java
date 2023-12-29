package com.example.controller;

import com.example.entity.Elevator;
import com.example.repository.ElevatorRepository;
import com.example.service.ElevatorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@RestController
@RequiredArgsConstructor
public class ElevatorController {

    private final ElevatorService elevatorService;
    private final ElevatorRepository elevatorRepository;

    @GetMapping("/update/{id}/{pressure}")
    public Elevator updateElevatorPressure(@PathVariable("id") int id, @PathVariable("pressure") double pressure) {
        // 기압 차이를 이용하여 현재 층수 계산
        int floor = elevatorService.calculateFloorFromPressure(pressure);

        // 엘리베이터 엔터티 업데이트 또는 저장 등의 로직을 수행
        Elevator elevator = elevatorRepository.findById(id).get();
        elevator.setFloor(floor);
        elevatorRepository.save(elevator);

        return elevator;
    }
}