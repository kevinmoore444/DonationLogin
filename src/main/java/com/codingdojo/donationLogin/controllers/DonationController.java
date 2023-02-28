package com.codingdojo.donationLogin.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.donationLogin.models.Donation;
import com.codingdojo.donationLogin.models.User;
import com.codingdojo.donationLogin.services.DonationService;
import com.codingdojo.donationLogin.services.UserService;

@Controller
public class DonationController {
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private UserService userService;
	
	
    //Render the dash-board, return to login screen if session is null
    @GetMapping("/donations")
    public String dashboard(HttpSession session, Model model) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	Long userId = (Long) session.getAttribute("userId");
    	model.addAttribute("loggedInUser", userService.oneUser(userId));
    	
    	model.addAttribute("donationList", donationService.allDonations());
    	return "dashboard.jsp";
    }
	
	//Create Donation - display form
    @GetMapping("/donations/new")
    public String displayNewDonationForm(@ModelAttribute("newDonation") Donation newDonation) {
    	return "newDonation.jsp";
    }
    
	
    //Create Donation - process form
	@PostMapping("/donations/new")
	public String processDonationForm(@Valid @ModelAttribute("newDonation") Donation newDonation, BindingResult result) {
		if(result.hasErrors()) {
			return "newDonation.jsp";
		}
		else {
			donationService.createDonation(newDonation);
			return "redirect:/donations";
		}
	}
	
	
	//Edit Donation - display form
	@GetMapping("/donations/edit/{id}")
	public String displayEditDonationForm(@PathVariable("id") Long id, Model model) {
		Donation foundDonation = donationService.oneDonation(id);
		model.addAttribute("foundDonation", foundDonation);
		return "editDonation.jsp";
	}
	
	//Edit Donation - process form
	@PutMapping("/donations/edit/{id}")
	public String processEditDonatino(@Valid @ModelAttribute("foundDonation") Donation donation, 
			BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			return "editDonation.jsp";
		}
		else {
			donationService.editDonation(donation);
			return "redirect:/donations";
		}
	}
	
	
	//Delete Donation
	@DeleteMapping("/donations/delete/{id}")
	public String processDeleteDonation(@PathVariable("id") Long id) {
		donationService.deleteDonation(id);
		return "redirect:/donations";
	}
	
	
	//Display one
	@GetMapping("/donations/{id}")
    public String donationDetails(@PathVariable("id") Long id, Model model) {
		Donation donation = donationService.oneDonation(id);
		model.addAttribute("donation", donation);
		return "donationDetails.jsp";
	}
	
	
	//Receive Donation - get user from session, get donation from path variable, send to service 
	@PutMapping("/donations/receive/{id}")
	public String receiveDonation(@PathVariable("id") Long donationId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.oneUser(userId);
		Donation donation = donationService.oneDonation(donationId);
		donationService.receiveDonation(loggedInUser, donation);
		return "redirect:/donations";
	}
	
	//Return Donation - get user from session, get donation from path variable, send to service 
	@PutMapping("/donations/return/{id}")
	public String returnDonation(@PathVariable("id") Long donationId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.oneUser(userId);
		Donation donation = donationService.oneDonation(donationId);
		donationService.returnDonation(loggedInUser, donation);
		return "redirect:/donations";
	}
	
	
	
	
}
