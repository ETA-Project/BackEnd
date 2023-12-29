package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Elevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                    // 엘리베이터 번호

    private int floor;                  // 엘리베이터의 현재 층수
}