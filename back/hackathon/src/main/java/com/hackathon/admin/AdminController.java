package com.hackathon.admin;

import java.util.List;

import com.hackathon.entity.Registration;
import com.hackathon.entity.Stats;
import com.hackathon.service.RegistrationService;
import com.hackathon.service.StatsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.entity.Member;
import com.hackathon.entity.News;
import com.hackathon.file.FileService;
import com.hackathon.service.NewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
	private final NewsService newsService;
	private final FileService fileService;
	private final StatsService statsService;
	private final RegistrationService registrationService;


	@GetMapping({"","/"})
	public String dash(
			Model model, @AuthenticationPrincipal Member user) {
		log.debug("inside ");
		if( user == null )	return "redirect:/logout";
		log.debug("dash : {}", user.isEnabled());
		List<Registration> regList = registrationService.getAllRegistrations();
		log.debug("regList : {}", regList);

		model.addAttribute("registers", regList);
		return "adm/dash";
	}


	@GetMapping({"/blog", "/blog/"})
	public String listBlog(
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("listBlog : {}", user.isEnabled());
		List<News> list1 = newsService.getAllNews();
		log.debug("list1 : {}", list1);

		model.addAttribute("blogList", list1);
		return "adm/blog-list";
	}
	
	@PostMapping("/blog/put")
	public String putBlog(
			String title, String summary, String sendUrl, String content,
			MultipartFile thumImage, MultipartFile bannerImage,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";		
		log.debug("putBlog: ");
		try {
			String thumPath = "";
			String bannerPath = "";
			if( thumImage != null && thumImage.getSize() > 0) {
				
				thumPath = fileService.saveFile(thumImage, (a)->{
					log.debug("{}", a);
				});
			}
			if( bannerImage != null && bannerImage.getSize() > 0) {
				
				bannerPath = fileService.saveFile(bannerImage, (a)->{
					log.debug("{}", a);
				});
			}
			News news = News.builder()
					.title(title)
					.content(content)
					.imageSrc(bannerPath)
					.build();

			News savednews = newsService.addNews(news);
			log.debug("putBlog: added news[{}]", savednews.getId());
		}catch(Exception e) {
			log.error("putBlog: exceptions {} ", e.getMessage());
		}
		return "redirect:/admin/blog";
	}
	@PostMapping("/register/put")
	public String putRegister(
			String name, String email, String phone,String subject, int age,

			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("putRegister: ");
		try {

			Registration registration = Registration.builder()
					.name(name)
					.email(email)
					.phone(phone)
					.subject(subject)
					.age(age)
					.build();

			Registration savedRegistration = registrationService.addRegistration(registration);
			log.debug("putBlog: added register[{}]", savedRegistration.getId());
		}catch(Exception e) {
			log.error("putBlog: exceptions {} ", e.getMessage());
		}
		return "redirect:/admin/blog";
	}

	@GetMapping("/blog/view")
	public String viewBlog(
			String id,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("listBlog : {}", user.isEnabled());
		try {
			long blogpk = Long.parseLong(id);
			News news = newsService.getNewsById(blogpk);
			model.addAttribute("blog", news);
			return "adm/blog-view";
		}catch(Exception e) {
			log.error("viewBlog: exceptions {}", e.getMessage());
		}
		
		return "redirect:/admin/blog";
	}

	@PostMapping("/blog/post")
	public String postBlog(
			String id,
			String title, String summary, String sendUrl, String content,
			MultipartFile thumImage, MultipartFile bannerImage,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";		
		log.debug("postBlog: ");
		try {
			Long pk = Long.parseLong(id);
			News orinews = newsService.getNewsById(pk);
			String bannerPath = orinews.getImageSrc();

			if( bannerImage != null && bannerImage.getSize() > 0) {				
				bannerPath = fileService.saveFile(bannerImage, (a)->{
					log.debug("{}", a);
				});
			}
			News news = News.builder()
					.id(pk)
					.title(title)
					.content(content)
					.imageSrc(bannerPath)
					.build();

			News savednews = newsService.addNews(news);
			log.debug("putBlog: added news[{}]", savednews.getId());
		}catch(Exception e) {
			log.error("putBlog: exceptions {} ", e.getMessage());
		}
		return "redirect:/admin/blog";
	}

	@GetMapping("/blog/deactive")
	public String deactiveNews(
			String id,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("deactiveNews : {}", user.isEnabled());
		try {
			long blogpk = Long.parseLong(id);
			newsService.removeNews(blogpk);
		}catch(Exception e) {
			log.error("deactiveNews: exceptions {}", e.getMessage());
		}

		return "redirect:/admin/blog";
	}

	@PostMapping("/stats/update")
	public String updateStats(@RequestParam int registerCount,
							  @RequestParam int onlinePassCount,
							  @RequestParam int offlinePassCount,
							  @RequestParam int hackerthonCount,
							  @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("updateStats: ",registerCount);
		statsService.updateStats(registerCount, onlinePassCount, offlinePassCount, hackerthonCount);
		return "redirect:/admin/blog";
	}







}
