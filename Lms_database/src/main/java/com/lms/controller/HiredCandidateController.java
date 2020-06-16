package com.lms.controller;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lms.exception.DataNotFoundException;
import com.lms.model.Response;
import com.lms.service.HiredCandidateService;
@RestController
@RequestMapping("/hiredcandidate")
public class HiredCandidateController {
	@Autowired
	private HiredCandidateService hiredCandidateService;

	 @PostMapping("/takecandidatelist")
	    public ResponseEntity<Response> importHiredCandidate(@RequestParam("file") MultipartFile filePath,
	                                                             @RequestParam String token) throws MessagingException, DataNotFoundException {
	        return new ResponseEntity<>(hiredCandidateService.dropHireCandidateInDataBase(filePath, token), HttpStatus.CREATED);
	    }

}