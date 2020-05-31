package ua.khpi.it.comparison.controller

import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ua.khpi.it.comparison.exception.StorageFileNotFoundException
import ua.khpi.it.comparison.service.storage.StorageService


@RestController
class ImageController(private val storageService: StorageService) {

    @GetMapping("api/images/{fileName}")
    @ResponseBody
    fun getImage(@PathVariable fileName: String): ResponseEntity<Resource?>? {
        val file: Resource = storageService.loadAsResource(fileName)
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; fileName=\"${file.filename}\"").body(file)
    }

    @PostMapping("api/images/upload")
    fun handleFileUpload(@RequestParam("file") file: MultipartFile,
                         @RequestParam(value = "fileName", required = false) fileName: String = "",
                         redirectAttributes: RedirectAttributes): ModelAndView {
        storageService.store(file, fileName)
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded ${file.originalFilename}!")
        return ModelAndView("redirect:/admin")
    }

    @PostMapping("api/images/{fileName}/delete")
    fun handleFileRemoval(@PathVariable fileName: String): ModelAndView {
        storageService.delete(fileName)
        return ModelAndView("redirect:/admin")
    }

    @ExceptionHandler(StorageFileNotFoundException::class)
    fun handleStorageFileNotFound(exc: StorageFileNotFoundException?): ResponseEntity<*>? {
        return ResponseEntity.notFound().build<Any>()
    }
}