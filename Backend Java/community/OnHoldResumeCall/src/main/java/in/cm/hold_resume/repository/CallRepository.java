package in.cm.hold_resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cm.hold_resume.model.Call;

public interface CallRepository extends JpaRepository<Call, Long> {
}