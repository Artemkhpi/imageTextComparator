package ua.khpi.it.comparison.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView
import ua.khpi.it.comparison.model.Interview
import ua.khpi.it.comparison.model.Interviewee
import ua.khpi.it.comparison.model.Sex
import ua.khpi.it.comparison.service.InterviewService
import ua.khpi.it.comparison.service.InterviewStepService
import ua.khpi.it.comparison.service.IntervieweeService
import ua.khpi.it.comparison.service.ProductDescriptionService
import java.sql.Date


@Controller
class InterviewController(private val intervieweeService: IntervieweeService,
                          private val interviewService: InterviewService,
                          private val interviewStepService: InterviewStepService,
                          private val productDescriptionService: ProductDescriptionService) {
    @PostMapping("api/interview/start")
    fun createInterview(@RequestParam steps: Int,
                        @RequestParam city: String,
                        @RequestParam education: String,
                        @RequestParam yearOfBirth: Long,
                        @RequestParam sex: Sex): RedirectView {
        val interviewee = intervieweeService.findExistingOrCreate(Interviewee(city =city, education = education, yearOfBirth = yearOfBirth, sex = sex))
        val descriptions = productDescriptionService.getRandomRecords(amount = steps)
        val interview = interviewService.add(Interview(steps = steps, intervieweeId = interviewee.intervieweeId!!, interviewStepsIdList = interviewService.createStepsForInterview(steps, descriptions)))
        return RedirectView("/interview/${interview.interviewId}/${(interview.interviewStepsIdList as ArrayList<Long>)[0]}")
    }

    @PostMapping("/interview/{interviewId}/{interviewStepId}")
    fun handleInterviewStep(@PathVariable interviewStepId: Long,
                            @PathVariable interviewId: Long,
                            @RequestParam userAnswer: Boolean): RedirectView {
        val interview = interviewService.findById(id = interviewId)!!
        val interviewStep = interviewStepService.findById(id = interviewStepId)!!
        interviewStep.userAnswer = userAnswer
        interviewStepService.update(interviewStep)
        interviewService.handleFinishedStep(interview, interviewStepId)
        return if (interview.isFinished) {
            RedirectView("/interview/finish")
        } else {
            RedirectView("/interview/$interviewId/${(interview.interviewStepsIdList as ArrayList<Long>)[0]}")
        }
    }

    @GetMapping("/interview/{interviewId}/{interviewStepId}")
    fun getInterviewPage(model: Model,
                         @PathVariable interviewId: Long,
                         @PathVariable interviewStepId: Long): String {
        val interview: Interview? = interviewService.findById(interviewId)
        interviewService.checkIfInterviewStepDataIsValid(interview, interviewStepId)
        interviewService.checkIfInterviewIsFinished(interview!!, interviewStepId)
        val interviewStep = interviewStepService.findById(interviewStepId)
        model.addAttribute("description", productDescriptionService.findById(interviewStep!!.descriptionId).content)
        model.addAttribute("image", interviewStep.imageName)
        model.addAttribute("interviewId", interviewId)
        model.addAttribute("interviewStepId", interviewStepId)
        return "interviewStep"
    }
}