package ua.khpi.it.comparison.controller

import org.apache.commons.io.IOUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ua.khpi.it.comparison.model.Report
import ua.khpi.it.comparison.service.ReportService
import javax.servlet.http.HttpServletResponse


@RestController
class ReportController(private val reportService: ReportService) {
    @GetMapping("/api/report")
    @ResponseBody
    fun generateReport(): Report {
        return reportService.generateReport()
    }

    @GetMapping("/api/report/txt")
    @ResponseBody
    fun generateTxtReport(response: HttpServletResponse) {
        val file = reportService.generateTxtReport()
        IOUtils.copy(file.inputStream(), response.outputStream)
        response.contentType = "application/txt"
        response.setHeader("Content-Disposition", "attachment; filename=${file.name}")
    }
}