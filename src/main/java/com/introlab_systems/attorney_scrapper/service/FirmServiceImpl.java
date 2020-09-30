package com.introlab_systems.attorney_scrapper.service;

import com.introlab_systems.attorney_scrapper.dao.FirmRepository;
import com.introlab_systems.attorney_scrapper.entity.Firm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FirmServiceImpl implements FirmService {

    FirmRepository firmRepository;

    public FirmServiceImpl(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    @Override
    public void save(Firm firm) {

    }
}
