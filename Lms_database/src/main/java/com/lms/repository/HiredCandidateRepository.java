package com.lms.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.model.HiredCandidate;

public interface HiredCandidateRepository extends JpaRepository<HiredCandidate ,Integer>{
	Optional<HiredCandidate> findByEmail( String email);

	List<HiredCandidate> findAll();	  
}
