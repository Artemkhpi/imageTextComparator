package ua.khpi.it.comparison.service.storage

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream


interface StorageService {
    fun init()
    fun store(file: MultipartFile, externalFilename: String)
    fun load(fileName: String): Path
    fun loadAsResource(fileName: String): Resource
    fun deleteAll()
    fun delete(fileName: String)
    fun loadAll(): Stream<Path>
    fun checkIfFileExists(fileName: String): Boolean
    fun getRandomFilename(): String
    fun getTotalAmount(): Long
}
