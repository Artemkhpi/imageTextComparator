package ua.khpi.it.comparison.db.entity

import ua.khpi.it.comparison.db.converter.ListToStringConverter
import javax.persistence.*

@Entity
@Table(name = "interview")
data class InterviewEntity(
        @Id
        @Column(name = "interview_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var interviewId: Long?,
        @Column(name = "steps")
        var steps: Int,
        @Column(name = "interviewee_id")
        var intervieweeId: Long,
        @Column(name = "finished")
        var isFinished: Boolean,
        @Convert(converter = ListToStringConverter::class)
        var interviewStepsIdList: MutableList<Long>,
        @Convert(converter = ListToStringConverter::class)
        var completedInterviewStepsIdList: MutableList<Long>
)
