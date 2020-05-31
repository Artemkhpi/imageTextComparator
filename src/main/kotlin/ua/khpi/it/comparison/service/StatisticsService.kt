package ua.khpi.it.comparison.service

import org.springframework.stereotype.Service

@Service
class StatisticsService(private val interviewService: InterviewService,
                        private val interviewStepService: InterviewStepService) {

    fun calculateImagesUsageMap(): Map<String, Long> {
        val images = mutableMapOf<String, Long>()
        interviewService.getAll()
                .flatMap { it.completedInterviewStepsIdList }
                .map { interviewStepService.findById(it) }
                .forEach {
                    if (it == null || it.imageName.isNullOrBlank()) return@forEach
                    if (images.containsKey(it.imageName!!)) {
                        images[it.imageName!!] = images[it.imageName!!]!!
                    } else {
                        images[it.imageName!!] = 1
                    }
                }
        return images.toList().sortedBy { (_, value) -> value }.toMap()
    }

    fun getTopFiveUsedImages(sortedImages: Map<String, Long>): Map<String, Long> {
        val topFiveSize = if (sortedImages.size > 5) 5 else sortedImages.size
        var keyValue = ""
        for (i in 0..topFiveSize) {
            if (i == topFiveSize - 1) keyValue = sortedImages.entries.toList()[i].key
        }
        val resultMap = mutableMapOf<String, Long>()
        for (entry in sortedImages) {
            resultMap[entry.key] = entry.value
            if (entry.key == keyValue) return resultMap
        }
        return resultMap
    }

    fun getTopFromFiveToTenUsedImages(sortedImages: Map<String, Long>): Map<String, Long> {
        if (sortedImages.size < 6) return emptyMap()
        val lastElementIndex = if (sortedImages.size > 10) 10 else sortedImages.size
        var lastKeyValue = ""
        for (i in 0..lastElementIndex) {
            if (i == lastElementIndex - 1) lastKeyValue = sortedImages.entries.toList()[i].key
        }
        val resultMap = mutableMapOf<String, Long>()
        var i = 0
        for (entry in sortedImages) {
            if (i++ < 5) continue
            resultMap[entry.key] = entry.value
            if (entry.key == lastKeyValue) return resultMap
        }
        return resultMap
    }
}