package com.lms.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.exception.DataNotFoundException;
import com.lms.message.MessageResponse;
import com.lms.model.FellowshipCandidate;
import com.lms.model.HiredCandidate;
import com.lms.repository.FellowshipCandidateRepository;
import com.lms.repository.HiredCandidateRepository;


@Service
public class FellowshipCandidateServiceImpl implements FellowshipCandidateService {
	@Autowired
	private FellowshipCandidateRepository fellowshipCandidateRepository;

	@Autowired
	private HiredCandidateRepository hiredCandidateRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JavaMailSender sender;

	/**
	 *@param id
	 *@return response(fellowshipCandidate)
	 */
	@Override
	public FellowshipCandidate onboardAcceptedCandidates(Integer id) {
		HiredCandidate hiredCandidate = hiredCandidateRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException(400, "User not found with this id"));
		FellowshipCandidate fellowshipCandidate = mapper.map(hiredCandidate, FellowshipCandidate.class);
		if (fellowshipCandidate.equals(null))
			throw new DataNotFoundException(400, "Null Values found");
		return fellowshipCandidateRepository.save(fellowshipCandidate);
	}

	/**
	 * @param fellowshipCandidate
	 * @throws MessagingException
	 * response(Sent email to candidates)
	 */
	@Override
	public void jobOfferNotification(FellowshipCandidate fellowshipCandidate) throws MessagingException, JsonProcessingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());  

		helper.setTo(fellowshipCandidate.getEmail());
		helper.setText("Dear " + fellowshipCandidate.getFirst_name() +
				"\n Please find attached the terms and conditions of your employment," +
				" should you accept this offer letter." +
				" We would like to have your response by [date]. In the meantime," +
				" please feel free to contact me or [Manager_name] via email or phone at [provide contact details]," +
				" if you have any questions.\n" +
				"\n We are all looking forward to having you on our team. ");
		helper.setSubject("Job offer notification");
		sender.send(message);
	} 

	/**
	 * @return Candidate count
	 */
	@Override
	public int CandidatesCount() {
		List<FellowshipCandidate> list = fellowshipCandidateRepository.findAll();
		return list.size();
	}

}
