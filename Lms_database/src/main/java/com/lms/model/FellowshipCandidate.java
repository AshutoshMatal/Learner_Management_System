package com.lms.model;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Table(name = "fellowship_candidate")
@Entity(name = "fellowship_candidate")
@Data
public class FellowshipCandidate {
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
	private String birth_date;
	private String is_birth_date_verified;
	private String parent_occupation;
	private String parent_contact_number;
	private String parent_annual_salary;
	private String local_address;
	private String permanent_address;
	private String photo_path;
	private String joining_date;
	private String candidate_status;
	private String personal_information;
	private String bank_information;
	private String educational_information;
	private String document_status;
	private String remark;
}
