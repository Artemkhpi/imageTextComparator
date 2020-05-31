package ua.khpi.it.comparison.service.storage

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import ua.khpi.it.comparison.exception.StorageException
import ua.khpi.it.comparison.exception.StorageFileNotFoundException
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream


@Service
class FileSystemStorageService : StorageService {

    private val rootLocation: Path = Paths.get("upload-dir")

    override fun loadAll(): Stream<Path> {
        return try {
            Files.walk(rootLocation, 1)
                    .filter { path: Path -> path != rootLocation }
                    .map { other: Path? -> rootLocation.relativize(other) }
        } catch (e: IOException) {
            throw StorageException("Failed to read stored files", e)
        }
    }

    override fun store(file: MultipartFile, externalFilename: String) {
        try {
            val fileName =
                    if (externalFilename.isBlank()) file.originalFilename!!
                    else externalFilename
            if (file.isEmpty) {
                throw StorageException("Failed to store empty file $fileName")
            }
            if (fileName.contains("..")) {
                // This is a security check
                throw StorageException("Cannot store file with relative path outside current directory $fileName")
            }
            if (checkIfFileExists("${rootLocation.fileName}/$fileName}")) {
                throw StorageException("File with name $fileName already exists")
            }
            file.inputStream.use { inputStream ->
                Files.copy(inputStream, rootLocation.resolve(fileName))
            }
        } catch (e: IOException) {
            throw StorageException(e.message)
        }
    }

    override fun load(fileName: String): Path {
        return rootLocation.resolve(fileName)
    }

    override fun loadAsResource(fileName: String): Resource {
        return try {
            val file = load(fileName)
            val resource: Resource = UrlResource(file.toUri())
            if (resource.exists() || resource.isReadable) {
                resource
            } else {
                throw StorageFileNotFoundException(
                        "Could not read file: $fileName")
            }
        } catch (e: MalformedURLException) {
            throw StorageFileNotFoundException("Could not read file: $fileName", e)
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun delete(fileName: String) {
        val file = File("${rootLocation.fileName}/$fileName")
        if (file.exists()) {
            file.delete()
        }
    }

    override fun getTotalAmount(): Long {
        return try {
            Files.walk(rootLocation, 1)
                    .filter { path: Path -> path != rootLocation }
                    .map { other: Path? -> rootLocation.relativize(other) }
                    .count()
        } catch (e: IOException) {
            throw StorageException("Failed to read stored files", e)
        }
    }

    final override fun init() {
        try {
            Files.createDirectories(rootLocation)
        } catch (e: IOException) {
            throw StorageException("Could not initialize storage", e)
        }
    }

    override fun checkIfFileExists(fileName: String): Boolean = File(fileName).exists()

    override fun getRandomFilename(): String {
        val files = File(rootLocation.toUri()).listFiles()
        if (files == null || files.isEmpty()) {
            throw StorageException("No upload images were found")
        } else {
            return files.map { it.name }.random()
        }
    }

    init {
        if (!File(rootLocation.fileName.toUri()).exists()) {
            init()
        }
    }

}
