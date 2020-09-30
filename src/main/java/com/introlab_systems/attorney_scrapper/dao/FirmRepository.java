package com.introlab_systems.attorney_scrapper.dao;

import com.introlab_systems.attorney_scrapper.entity.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firm,Long> {
}
