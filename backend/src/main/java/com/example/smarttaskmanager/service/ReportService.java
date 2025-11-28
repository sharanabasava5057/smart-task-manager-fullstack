package com.example.smarttaskmanager.service;

import com.example.smarttaskmanager.entity.Task;
import com.example.smarttaskmanager.entity.TaskStatus;
import com.example.smarttaskmanager.repository.TaskRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TaskRepository taskRepository;

    // ----------------------------------------------------
    //  TASKS BY STATUS  (called by ReportController)
    // ----------------------------------------------------

    /** PDF: /api/reports/status?status=TODO */
    public byte[] generateTasksByStatusPdf(String statusString) throws Exception {
        TaskStatus status = TaskStatus.valueOf(statusString);
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(t -> t.getStatus() == status)
                .toList();

        return createPdf(tasks, "Tasks with status: " + statusString);
    }

    /** EXCEL: /api/reports/status/excel?status=TODO */
    public byte[] generateTasksByStatusExcel(String statusString) throws IOException {
        TaskStatus status = TaskStatus.valueOf(statusString);
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(t -> t.getStatus() == status)
                .toList();

        return createExcel(tasks, "Tasks with status: " + statusString);
    }

    // ----------------------------------------------------
    //  TASKS BY DATE RANGE (called by ReportController)
    // ----------------------------------------------------

    /** PDF: /api/reports/dates?start=2025-01-01&end=2025-01-31 */
    public byte[] generateTasksByDateRangePdf(LocalDate start, LocalDate end) throws Exception {
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(t -> t.getDueDate() != null
                        && !t.getDueDate().isBefore(start)
                        && !t.getDueDate().isAfter(end))
                .toList();

        return createPdf(tasks, "Tasks from " + start + " to " + end);
    }

    /** EXCEL: /api/reports/dates/excel?start=2025-01-01&end=2025-01-31 */
    public byte[] generateTasksByDateRangeExcel(LocalDate start, LocalDate end) throws IOException {
        List<Task> tasks = taskRepository.findAll().stream()
                .filter(t -> t.getDueDate() != null
                        && !t.getDueDate().isBefore(start)
                        && !t.getDueDate().isAfter(end))
                .toList();

        return createExcel(tasks, "Tasks from " + start + " to " + end);
    }

    // ----------------------------------------------------
    //  INTERNAL PDF CREATION
    // ----------------------------------------------------

    private byte[] createPdf(List<Task> tasks, String title) throws Exception {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph(title + "\n\n"));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);

        addHeaderCell(table, "Title");
        addHeaderCell(table, "Status");
        addHeaderCell(table, "Priority");
        addHeaderCell(table, "Due Date");
        addHeaderCell(table, "Assigned To");
        addHeaderCell(table, "Created By");

        for (Task t : tasks) {
            table.addCell(safe(t.getTitle()));
            table.addCell(t.getStatus() == null ? "" : t.getStatus().name());
            table.addCell(t.getPriority() == null ? "" : t.getPriority().name());
            table.addCell(t.getDueDate() == null ? "" : t.getDueDate().toString());
            table.addCell(
                    t.getAssignedTo() == null || t.getAssignedTo().getEmail() == null
                            ? ""
                            : t.getAssignedTo().getEmail()
            );
            table.addCell(
                    t.getCreatedBy() == null || t.getCreatedBy().getEmail() == null
                            ? ""
                            : t.getCreatedBy().getEmail()
            );
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }

    // ----------------------------------------------------
    //  INTERNAL EXCEL CREATION
    // ----------------------------------------------------

    private byte[] createExcel(List<Task> tasks, String sheetTitle) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        int rowIdx = 0;

        // Header
        Row header = sheet.createRow(rowIdx++);
        header.createCell(0).setCellValue("Title");
        header.createCell(1).setCellValue("Status");
        header.createCell(2).setCellValue("Priority");
        header.createCell(3).setCellValue("Due Date");
        header.createCell(4).setCellValue("Assigned To");
        header.createCell(5).setCellValue("Created By");

        // Data rows
        for (Task t : tasks) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(safe(t.getTitle()));
            row.createCell(1).setCellValue(t.getStatus() == null ? "" : t.getStatus().name());
            row.createCell(2).setCellValue(t.getPriority() == null ? "" : t.getPriority().name());
            row.createCell(3).setCellValue(t.getDueDate() == null ? "" : t.getDueDate().toString());
            row.createCell(4).setCellValue(
                    t.getAssignedTo() == null || t.getAssignedTo().getEmail() == null
                            ? ""
                            : t.getAssignedTo().getEmail()
            );
            row.createCell(5).setCellValue(
                    t.getCreatedBy() == null || t.getCreatedBy().getEmail() == null
                            ? ""
                            : t.getCreatedBy().getEmail()
            );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        return out.toByteArray();
    }

    // ----------------------------------------------------
    //  HELPERS
    // ----------------------------------------------------

    private void addHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(
                new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11))
        );
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
    }

    private String safe(String val) {
        return val == null ? "" : val;
    }
}
