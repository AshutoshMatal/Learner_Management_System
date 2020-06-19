package com.lms.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lms.dto.HiredCandidateDTO;
import com.lms.exception.DataNotFoundException;
import com.lms.model.HiredCandidate;
import com.lms.repository.HiredCandidateRepository;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import  static com.lms.message.CandidateResponse.*;

@Service
public class HiredCandidateServiceImpl implements HiredCandidateService{

	@Autowired
	private HiredCandidateRepository hiredCandidateRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	private final String rejected = String.valueOf(REJECTED);
	private final String accepted = String.valueOf(ACCEPTED);
	private final String pending = String.valueOf(PENDING);

	@Override
	public boolean getHiredCandidate(MultipartFile filePath)  {
		boolean flag = true;
		HiredCandidateDTO hiredCandidateDto = new HiredCandidateDTO();
		try (InputStream fis = filePath.getInputStream()) {
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
			Iterator rows = sheet.rowIterator();
			XSSFCell cell;
			//For each row, iterate through all the columns
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				if (!flag) {
					while (cells.hasNext()) {
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setId((int) cell.getNumericCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setFirst_name(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setMiddle_name(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setLast_name(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setEmail(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setHired_city(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setDegree(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setHired_date(cell.getDateCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setContact_number((long) cell.getNumericCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setPermanent_pincode((int) cell.getNumericCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setHired_lab(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setAttitude(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setCommunication_remark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setKnowledge_remark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setAggregate_remark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setStatus(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setCreator_stamp(LocalDateTime.now());
						cell = (XSSFCell) cells.next();
						hiredCandidateDto.setCreator_user(hiredCandidateDto.getId());
						save(hiredCandidateDto);
						sendSelectionMail(hiredCandidateDto.getEmail());
					}
				}
				flag = false;
			}
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void save(HiredCandidateDTO hiredCandidateDto) {
		HiredCandidate hiredCandidate = mapper.map(hiredCandidateDto, HiredCandidate.class);
		if (hiredCandidate.equals(null))
			throw new DataNotFoundException(400, "Null Values found");
		hiredCandidateRepository.save(hiredCandidate);
	}

	private void sendSelectionMail(String email) throws MessagingException  {
		Optional<HiredCandidate> hiredCandidate = hiredCandidateRepository.findByEmail(email);

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		final Context ctx = new Context();
		ctx.setVariable("name", hiredCandidate.get().getFirst_name());
		ctx.setVariable("acceptLink", "http://localhost:8080/hirecandidate/update?candidateResponse=ACCEPTED&emailId=" + email);
		ctx.setVariable("rejectLink", "http://localhost:8080/hirecandidate/update?candidateResponse=REJECTED&emailId=" + email);
		String html = templateEngine.process("sendmail", ctx);
		helper.setTo(email);
		helper.setText(html, true);
		helper.setSubject("Fellowship Shortlist");
		sender.send(message);
	}

	@Override
	public List<HiredCandidate> getAllHiredCandidates() 
	{
		List<HiredCandidate> list = hiredCandidateRepository.findAll();
		if (list.equals(null))
			throw new DataNotFoundException(400, "Null Values found");
		return list;
	}

	@Override
	public HiredCandidate viewCandidateProfile(Integer id) throws DataNotFoundException 
	{
		return hiredCandidateRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException(400, "No such id found"));
	}
}



