package com.sentiment.demo.controller;

import com.sentiment.demo.dto.HistoryResponseDTO;
import com.sentiment.demo.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public List<HistoryResponseDTO> getHistory(@RequestParam(defaultValue = "50") int limit) {
        if (limit < 1) limit = 1;
        if (limit > 1000) limit = 1000;
        return historyService.getStatHistory(limit);
    }
}
