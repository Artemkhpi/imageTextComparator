package ua.khpi.it.comparison.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ua.khpi.it.comparison.model.Report
import ua.khpi.it.comparison.service.*
import ua.khpi.it.comparison.service.storage.StorageService


@Controller
class StatisticsController(private val intervieweeService: IntervieweeService,
                           private val interviewService: InterviewService,
                           private val statisticsService: StatisticsService,
                           private val productDescriptionService: ProductDescriptionService,
                           private val storageService: StorageService) {
    @GetMapping("/")
    fun getStatisticsDashboard(model: Model): String {
        val images = statisticsService.calculateImagesUsageMap()
        val topFiveImages = statisticsService.getTopFiveUsedImages(images)
        if (images.size > 5) {
            model.addAttribute("imagesPartTwo", statisticsService.getTopFromFiveToTenUsedImages(images))
        }
        model.addAttribute("imagesPartOne", topFiveImages)
        model.addAttribute("usersTotal", intervieweeService.getTotalAmount())
        model.addAttribute("interviewsTotal", interviewService.getTotalAmount())
        model.addAttribute("interviewsFinished", interviewService.getFinishedAmount())
        model.addAttribute("descriptionsCreated", productDescriptionService.getTotalAmount())
        model.addAttribute("imagesUploaded", storageService.getTotalAmount())
        return "statistics"
    }
}