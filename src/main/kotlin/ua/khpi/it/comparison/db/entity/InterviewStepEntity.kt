package ua.khpi.it.comparison.db.entity

import javax.persistence.*

@Entity
@Table(name = "interview_step")
data class InterviewStepEntity(
        @Id
        @Column(name = "step_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var stepId: Long?,
        @Column(name = "image_name")
        var imageName: String,
        @Column(name = "description_id")
        var descriptionId: Long,
        @Column(name = "user_answer")
        var userAnswer: Boolean?,
        @Column(name = "correct_answer")
        var correctAnswer: Boolean
)
