package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // -------------------------------------------------
    //  REPORT BY STATUS (PDF)
    // -------------------------------------------------
    @GetMapping("/status")
    public ResponseEntity<byte[]> reportByStatusPdf(@RequestParam String status) throws Exception {
        byte[] pdf = reportService.generateTasksByStatusPdf(status);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=status-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // -------------------------------------------------
    //  REPORT BY STATUS (EXCEL)
    // -------------------------------------------------
    @GetMapping("/status/excel")
    public ResponseEntity<byte[]> reportByStatusExcel(@RequestParam String status) throws Exception {
        byte[] excel = reportService.generateTasksByStatusExcel(status);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=status-report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }

    // -------------------------------------------------
    //  REPORT BY DATE RANGE (PDF)
    // -------------------------------------------------
    @GetMapping("/dates")
    public ResponseEntity<byte[]> reportByDateRangePdf(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) throws Exception {

        byte[] pdf = reportService.generateTasksByDateRangePdf(start, end);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=date-range-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // -------------------------------------------------
    //  REPORT BY DATE RANGE (EXCEL)
    // -------------------------------------------------
    @GetMapping("/dates/excel")
    public ResponseEntity<byte[]> reportByDateRangeExcel(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) throws Exception {

        byte[] excel = reportService.generateTasksByDateRangeExcel(start, end);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=date-range-report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }
}
