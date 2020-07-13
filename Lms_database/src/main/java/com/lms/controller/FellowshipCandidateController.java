package com.lms.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.configuration.ApplicationConfiguration;
import com.lms.model.FellowshipCandidate;
import com.lms.model.Response;
import com.lms.service.FellowshipCandidateServiceImpl;

@RequestMapping("/fellowshipcandidate")
@RestController
public class FellowshipCandidateController {


	@Autowired
	FellowshipCandidateServiceImpl fellowshipCandidateService;

	/**
	 * @param id
	 * @return
	 * @throws MessagingException
	 * @throws JsonProcessingException
	 */
	@PostMapping("/joincandidate")
	public ResponseEntity<Response> joinCandidate(@RequestParam(value = "id") Integer id) throws MessagingException, JsonProcessingException {
		FellowshipCandidate fellowshipCandidate = fellowshipCandidateService.onboardAcceptedCandidates(id);
		fellowshipCandidateService.jobOfferNotification(fellowshipCandidate);
		return new ResponseEntity<>(new Response(ApplicationConfiguration.getMessageAccessor().getMessage("111"),fellowshipCandidate), HttpStatus.OK);
	}

	/**
	 * @return
	 */
	@GetMapping("/candidatecount")
	public ResponseEntity<Response> candidatesCount() {
		int candidateCount = fellowshipCandidateService.CandidatesCount();
		return new ResponseEntity<>(new Response(ApplicationConfiguration.getMessageAccessor().getMessage("112"),candidateCount), HttpStatus.OK);
	}


}
