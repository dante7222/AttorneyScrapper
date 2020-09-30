package com.introlab_systems.attorney_scrapper.service;

import com.introlab_systems.attorney_scrapper.dao.AttorneyRepository;
import com.introlab_systems.attorney_scrapper.entity.Attorney;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AttorneyServiceImpl implements AttorneyService {

    AttorneyRepository attorneyRepository;

    public AttorneyServiceImpl(AttorneyRepository attorneyRepository) {
        this.attorneyRepository = attorneyRepository;
    }

    @Override
    public void save(Attorney attorney) {
        attorneyRepository.save(attorney);
    }
}
