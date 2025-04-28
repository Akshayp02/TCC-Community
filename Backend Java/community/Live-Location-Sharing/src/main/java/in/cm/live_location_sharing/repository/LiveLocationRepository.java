package in.cm.live_location_sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cm.live_location_sharing.model.LiveLocationModel;

public interface LiveLocationRepository extends JpaRepository<LiveLocationModel, Long> {

}
