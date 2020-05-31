package ua.khpi.it.comparison.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import ua.khpi.it.comparison.exception.ApiException


@Controller
class ExceptionController {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(model: Model, exception: ApiException): String {
        model.addAttribute("message", exception.message)
        return "error"
    }

}