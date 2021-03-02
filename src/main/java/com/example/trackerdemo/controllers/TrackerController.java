package com.example.trackerdemo.controllers;

import com.example.trackerdemo.services.TrackerDataService;
import com.example.trackerdemo.models.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//rendering HTML, not REST controller
@Controller
public class TrackerController {

    @Autowired
    TrackerDataService trackerDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<DataRow> dataRows = trackerDataService.getAllRows();
        int totalReportedCases = dataRows.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = dataRows.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        //spring rendering value for the model, with variable name and value
        model.addAttribute("dataRows", dataRows);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}