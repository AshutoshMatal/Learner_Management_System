package com.lms.model;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "user_details")
@Table(name = "user_details")
@Data
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String email;

	private String first_name;

	private String last_name;

	private String password;

	private long contact_number;

	private boolean verified=false;

	private LocalDateTime creator_stamp;

	private String creator_user;
}
