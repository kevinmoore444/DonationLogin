package com.codingdojo.donationLogin.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="donations")
public class Donation {

	//Attributes
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotNull
    @Size(min = 5, max = 200)
    private String donationName;
	    
    @NotNull
    @Min(0)
    private Integer quantity;
    
    @NotNull
    @Size(min = 5, max = 200)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="donor_id") //foreign key
    private User donor;
    
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){
	   this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	   this.updatedAt = new Date();
	}
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_donations", 
        joinColumns = @JoinColumn(name = "donation_id"), 
        inverseJoinColumns = @JoinColumn(name = "receiver_id")
    )
    private List<User> receivers;
    
    
    
    
    //Constructor
	public Donation() {}
	
	
	
	//Generate Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDonationName() {
		return donationName;
	}
	public void setDonationName(String donationName) {
		this.donationName = donationName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getDonor() {
		return donor;
	}
	public void setDonor(User donor) {
		this.donor = donor;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<User> getReceivers() {
		return receivers;
	}
	public void setReceivers(List<User> receivers) {
		this.receivers = receivers;
	}
	
	
	
	
}
