package in.cm.current_location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cm.current_location.model.CurrentLocationModel;

public interface CurrentLocationRepo extends JpaRepository<CurrentLocationModel, Long>{

}
