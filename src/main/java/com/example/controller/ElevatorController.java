package com.example.controller;

import com.example.entity.Elevator;
import com.example.repository.ElevatorRepository;
import com.example.service.ElevatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ETA API", description = "Swagger 테스트용 API")
@RestController
@RequiredArgsConstructor
public class ElevatorController {

    private final ElevatorService elevatorService;
    private final ElevatorRepository elevatorRepository;

    @Operation(summary = "엘리베이터 번호 입력", description = "해당 번호의 엘리베이터 층수를 반환합니다.")
    @Parameter(name="id", description = "엘리베이터 번호")
    @GetMapping("/eta/check/{id}")
    public int getElevatorFloor(@PathVariable("id") int id) {
        // elevatorRepository에서 id를 통해 엘리베이터 객체 불러옴
        Elevator elevator = elevatorRepository.findById(id).get();

        // 해당 객체에 저장된 층수를 반환
        return elevator.getFloor();
    }

    @Operation(summary = "엘리베이터의 기압 입력", description = "엘리베이터 번호와 기압을 입력받아 층수를 반환합니다.")
    @Parameter(name = "id", description = "엘리베이터 번호")
    @Parameter(name = "pressure", description = "엘리베이터의 기압")
    @GetMapping("/eta/update/{id}/{pressure}")
    public Elevator updateElevatorPressure(@PathVariable("id") int id, @PathVariable("pressure") double pressure) {
        // 기압 차이를 이용하여 현재 층수 계산
        int floor = elevatorService.calculateFloorFromPressure(pressure);

        // 엘리베이터 엔터티 업데이트 또는 저장 등의 로직을 수행
        Elevator elevator = elevatorRepository.findById(id).get();
        elevator.setFloor(floor);
        elevatorRepository.save(elevator);

        return elevator;
    }

    @Operation(summary = "기압 초기화", description = "1층의 기압을 통해 초기화를 시킵니다.")
    @Parameter(name = "base", description = "초기화 기압")
    @Parameter(name = "per", description = "층별 기압 차")
    @PostMapping("/eta/init/{base}/{per}")
    public String updateInit(@PathVariable("base") double basePressure, @PathVariable("per") double pressureFloor) {
        // 받은 기압들을 토대로 초기화
        return elevatorService.pressureInit(basePressure, pressureFloor) ? "초기화 성공!" : "오류 발생..";
    }
}