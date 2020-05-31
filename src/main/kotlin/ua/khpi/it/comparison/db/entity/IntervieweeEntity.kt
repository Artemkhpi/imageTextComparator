package ua.khpi.it.comparison.db.entity

import ua.khpi.it.comparison.model.Sex
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "interviewee")
data class IntervieweeEntity(
        @Id
        @Column(name = "interviewee_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var intervieweeId: Long?,
        @Column(name = "city")
        var city: String,
        @Column(name = "education")
        var education: String,
        @Column(name = "yearOfBirth")
        var yearOfBirth: Long,
        @Enumerated(EnumType.STRING)
        @Column(length = 6, name = "sex")
        var sex: Sex
)