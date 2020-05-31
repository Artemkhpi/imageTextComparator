package ua.khpi.it.comparison.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.web.bind.annotation.RequestParam
import java.sql.Date

data class Interviewee(
        @JsonIgnore
        var intervieweeId: Long? = null,
        var city: String,
        var education: String,
        var yearOfBirth: Long,
        var sex: Sex
)

enum class Sex { MALE, FEMALE }
