package com.mybatis.mybatis.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UuserService implements  IUerService {
    @Override
    public void insertUser() {

        System.out.println("插入--插入---------- ");
    }


    public static void main(String[] args) {
        Date date =new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDate.now());
    }
}
