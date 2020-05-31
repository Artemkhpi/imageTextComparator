package ua.khpi.it.comparison.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import ua.khpi.it.comparison.model.ProductDescription
import ua.khpi.it.comparison.service.ProductDescriptionService


@Controller
class ProductDescriptionController(private val productDescriptionService: ProductDescriptionService) {

    @PostMapping("api/description/{id}/delete")
    fun deleteProductDescription(@PathVariable id: String): ModelAndView {
        productDescriptionService.remove(id.toLong())
        return ModelAndView("redirect:/admin")
    }

    @PostMapping("api/description")
    fun createProductDescription(@RequestParam description: String,
                                 @RequestParam image: String): ModelAndView {
        productDescriptionService.add(ProductDescription(correctImageName = image, content = description, descriptionId = null))
        return ModelAndView("redirect:/admin")
    }

}