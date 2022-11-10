package com.example.demo.controller;


import com.example.demo.datasource.Slave;
import com.example.demo.entity.Club;
import com.example.demo.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private ClubService clubService;

    @GetMapping("select")
    @Slave
    public Club select() {
        return clubService.getById(1);
    }

    @GetMapping("select2")
    public Club select2() {
        return clubService.getById(1);
    }

    @PostMapping("insert")
    public void insert() {
        Club club=new Club();
        club.setName("");
        club.setIntroduction("");

        clubService.save(club);

        return ;
    }

}
