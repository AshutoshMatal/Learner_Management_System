package com.lms.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.lms.dto.HiredCandidateDTO;
import com.lms.exception.DataNotFoundException;
import com.lms.model.HiredCandidate;

public interface HiredCandidateService {

	public HiredCandidate viewCandidateProfile(Integer id) throws DataNotFoundException;

}
