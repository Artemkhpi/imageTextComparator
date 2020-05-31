package ua.khpi.it.comparison.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ua.khpi.it.comparison.service.ProductDescriptionService
import ua.khpi.it.comparison.service.storage.StorageService
import java.util.stream.Collectors


@Controller
class NavigationController(private val productDescriptionService: ProductDescriptionService,
                           private val storageService: StorageService) {
    @GetMapping("/admin")
    fun getAdminPage(model: Model): String {
        model.addAttribute("images",
                storageService.loadAll().map { path -> path.fileName.toString()}.collect(Collectors.toList()))
        model.addAttribute("descriptions", productDescriptionService.getAll())
        return "admin"
    }

    @GetMapping("/image-upload")
    fun getImageUploadPage(model: Model): String {
        return "addImage"
    }

    @GetMapping("/description-create")
    fun getDescriptionCreatePage(model: Model): String {
        model.addAttribute("images",
                storageService.loadAll().map { path -> path.fileName.toString()}.collect(Collectors.toList()))
        return "addDescription"
    }

    @GetMapping("/interview")
    fun getInterviewPage(model: Model): String {
        return "interview"
    }

    @GetMapping("/interview/finish")
    fun getInterviewResultPage(model: Model): String {
        return "interviewFinish"
    }

    @GetMapping("/error")
    fun getErrorPage(model: Model,
                     redirectAttributes: RedirectAttributes): String {
        return "error"
    }
}