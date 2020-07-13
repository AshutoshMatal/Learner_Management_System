package com.lms.controller;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lms.configuration.ApplicationConfiguration;
import com.lms.exception.DataNotFoundException;
import com.lms.model.HiredCandidate;
import com.lms.model.Response;
import com.lms.service.HiredCandidateService;

@RequestMapping("/hiredcandidate")
@RestController
public class HiredCandidateController {
	@Autowired
	private HiredCandidateService hiredCandidateService;

	/**
	 * @param filePath
	 * @return
	 * @throws MessagingException
	 * @throws DataNotFoundException
	 */
	@PostMapping("/takecandidatelist")
	public ResponseEntity<Response> importHiredCandidate(@RequestParam("file") MultipartFile filePath) throws MessagingException, DataNotFoundException {
		boolean response=hiredCandidateService.getHiredCandidate(filePath);
		return new ResponseEntity<Response>(new Response(ApplicationConfiguration.getMessageAccessor()
				.getMessage("109"),response), HttpStatus.CREATED);
	}

	/**
	 * @return
	 */
	@GetMapping("/hiredcandidatelist")
	public ResponseEntity<List> hiredCandidate() {
		List list = hiredCandidateService.getAllHiredCandidates();
		return new ResponseEntity<List>(list, HttpStatus.MULTI_STATUS);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/viewcandidateprofile")
	public ResponseEntity<Response> viewCandidateProfile(@RequestParam(value = "id") Integer id) {
		HiredCandidate hiredCandidate = hiredCandidateService.viewCandidateProfile(id);
		return new ResponseEntity<Response>(new Response(ApplicationConfiguration.getMessageAccessor().getMessage("105"),hiredCandidate), HttpStatus.OK);
	}

	/**
	 * @param candidateResponse
	 * @param email
	 * @return
	 * @throws DataNotFoundException
	 */
	@GetMapping("/update")
	public ResponseEntity<Response> update(@RequestParam String candidateResponse,
			@RequestParam String email) throws DataNotFoundException {
		HiredCandidate hiredCandidate= hiredCandidateService.update(candidateResponse, email);
		return new ResponseEntity<Response>(new Response(ApplicationConfiguration.getMessageAccessor().getMessage("110"),hiredCandidate), HttpStatus.OK);
	}
}