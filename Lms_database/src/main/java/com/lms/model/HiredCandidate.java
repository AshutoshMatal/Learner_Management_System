package com.lms.model;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Table(name="hired_candidate")
@Entity(name="hired_candidate")
@Data
public class HiredCandidate {
	@Id
	private int id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String email;
	private String hired_city;
	private String degree;
	private Date hired_date;
	private int contact_number;
	private int permanent_pincode;
	private String hired_lab;
	private String attitude;
	private String communication_remark;
	private String knowledge_remark;
	private String aggregate_remark;
	private String status;
	private LocalDateTime creator_stamp;
	private String creator_user;
	
}