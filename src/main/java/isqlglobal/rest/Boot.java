package isqlglobal.rest;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isqlglobal.entity.Enquiry;
import isqlglobal.entity.Mail;
import isqlglobal.entity.Module;
import isqlglobal.entity.Reviews;
import isqlglobal.service.EmailService;
import isqlglobal.service.EnquiryService;
import isqlglobal.service.ModuleService;
import isqlglobal.service.ReviewService;



@RestController
@RequestMapping("/rest-api")
@CrossOrigin(origins={"http://localhost:4200"})
public class Boot {
	@Autowired
	ModuleService moduleService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	EnquiryService enquiryService;
	@Autowired
	    private EmailService emailService;
  @RequestMapping("/modules")
  public List<Module> getModules()
  {
	  return moduleService.getModules();
  }
  
  @RequestMapping("/reviews")
  public List<Reviews> getReviews()
  {
	  return reviewService.getReviews();
  }
  
  @RequestMapping(value="/enquiries",method=RequestMethod.POST)
  public Enquiry addEnquiry(@RequestBody Enquiry message){
	  Mail m=new Mail();
	  Map model = new HashMap();
      model.put("name", message.getName());
      model.put("location", "India");
      model.put("message", message.getMessage());
      model.put("signature", "https://xyzcourses.com");
      m.setModel(model);
	  m.setFrom("vinodh@isqlglobal.com");
	  m.setTo(message.getEmail());
	  m.setSubject("Enquiry made for a course");
	  m.setContent(message.getMessage());
	   try {
			emailService.sendSimpleMessage(m);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return enquiryService.addEnquiry(message);
	  
  }
  
  @RequestMapping("/enquiries")
  public List<Enquiry> getEnquiries()
  {
	  return enquiryService.getEnquiries();
  }
  
  @RequestMapping(value="/reviews",method=RequestMethod.POST)
  public Reviews addMessage(@RequestBody Reviews message){
	  return reviewService.addReviews(message);
	  
  }
  
  @RequestMapping(value="/mailer",method=RequestMethod.POST)
  public Mail addMessage(@RequestBody Mail mail){
	 
	  Map model = new HashMap();
      model.put("name", "Memorynotfound.com");
      model.put("location", "Belgium");
      model.put("signature", "https://memorynotfound.com");
      mail.setModel(model);

      try {
		emailService.sendSimpleMessage(mail);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return mail;
  }
  
  @RequestMapping("/modules/{id}")
  public Module getModules(@PathVariable String id){
	  return moduleService.getModules(id);
  }
  
  }
