package com.codingdojo.donationLogin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.donationLogin.models.Donation;
import com.codingdojo.donationLogin.models.User;
import com.codingdojo.donationLogin.repositories.DonationRepository;

@Service
public class DonationService {

	
	@Autowired
	private DonationRepository donationRepo;
	
	
	//all donations
	public List<Donation> allDonations(){
		return donationRepo.findAll();
	}
	
	//Create one
	public Donation createDonation(Donation donation) {
		return donationRepo.save(donation);
		
	}
	
	
	//find One
	public Donation oneDonation(Long id) {
		Optional<Donation> optionalDonation = donationRepo.findById(id);
		if(optionalDonation.isPresent()) {
			return optionalDonation.get();
		}
		else {
			return null;
		}
	}
			
		
	//edit
	public Donation editDonation(Donation donation) {
		return donationRepo.save(donation);
	}
		
		
	//delete
	public void deleteDonation(Long id) {
		donationRepo.deleteById(id);
	}
	
	
	public void receiveDonation(User user, Donation donation) {
		
		//Sometimes need to instantiate a list, since get Receivers is a list
		//	if(donation.getReceivers() == null) {
		//	new ArrayList<Donation>() = donation.getReceivers();
		//	}
		donation.getReceivers().add(user);
		donation.setQuantity(donation.getQuantity()-1);
		donationRepo.save(donation);
	}
	
	
	public void returnDonation(User user, Donation donation) {
		donation.getReceivers().remove(user);
		donation.setQuantity(donation.getQuantity()+1);
		donationRepo.save(donation);
	}
	
	
	
}
