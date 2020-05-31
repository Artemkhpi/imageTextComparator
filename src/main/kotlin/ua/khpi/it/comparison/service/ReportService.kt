package ua.khpi.it.comparison.service

import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Service
import ua.khpi.it.comparison.model.ImageReport
import ua.khpi.it.comparison.model.Report
import java.io.File
import java.nio.charset.Charset

@Service
class ReportService(private val interviewService: InterviewService,
                    private val intervieweeService: IntervieweeService,
                    private val interviewStepService: InterviewStepService,
                    private val descriptionService: ProductDescriptionService) {

    fun generateReport(): Report {
        val report: Report = Report()
        interviewService.getAll()
                .forEach {
                    for (stepId in it.completedInterviewStepsIdList) {
                        val interviewStep = interviewStepService.findById(stepId) ?: return@forEach
                        report.images[interviewStep.imageName!!] = ImageReport(
                                description = descriptionService.findById(interviewStep.descriptionId).content,
                                usedAnswer = interviewStep.userAnswer!!,
                                predefinedAnswer = interviewStep.correctAnswer!!,
                                user = intervieweeService.findById(it.intervieweeId))
                    }
                }
        return report
    }

    fun generateTxtReport(): File {
        val reportFile: File = File.createTempFile("report-", ".txt")
        FileUtils.writeStringToFile(reportFile, Gson().toJson(generateReport()), Charsets.UTF_8)
        return reportFile
    }
}