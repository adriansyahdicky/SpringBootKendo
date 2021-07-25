package com.apotik.controller.api;

import com.apotik.entity.History;
import com.apotik.service.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/api/history")
    public List<History> getHistorys(){
        return historyService.getHistorys();
    }

}
