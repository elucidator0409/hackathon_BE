//package com.hackathon.controller;
//
//import com.hackathon.entity.News;
//import com.hackathon.repository.NewsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Value("${com.atcollabo.uploadpath}") // in application-properties
//    private String uploadPath;
//
//
//    @Autowired
//    private NewsRepository newsRepository;
//
//
//
//    @GetMapping("/create-news")
//    public String showCreateNewsForm(Model model) {
//        model.addAttribute("news", new News());
//        return "create-news-form";
//    }
//
//    private String saveImage(MultipartFile imageFile) {
//        try {
//            String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
//            String uploadDir = uploadPath;
//            Path filePath = Paths.get(uploadDir, fileName);
//            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            return "/uploads/" + fileName; // Return the path to save in the news entity
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @PostMapping("/create-news")
//    public String createNews(@ModelAttribute News news,@RequestParam("imageFile") MultipartFile imageFile) {
//        if (!imageFile.isEmpty()) {
//            String imageSrc = saveImage(imageFile); // Implement this method to save the image and return the path
//            news.setImageSrc(imageSrc);
//        }
//        newsRepository.save(news);
//        return "redirect:/admin/create-news";
//    }
//}
