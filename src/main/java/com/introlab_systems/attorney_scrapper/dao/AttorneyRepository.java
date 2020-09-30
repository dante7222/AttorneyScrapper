package com.introlab_systems.attorney_scrapper.dao;

import com.introlab_systems.attorney_scrapper.entity.Attorney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttorneyRepository extends JpaRepository<Attorney,Long> {

}
