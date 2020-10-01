package com.introlab_systems.attorney_scrapper.repository;

import com.introlab_systems.attorney_scrapper.model.entity.Attorney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttorneyRepository extends JpaRepository<Attorney,Long> {

}
