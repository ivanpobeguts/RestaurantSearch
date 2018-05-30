package com.pobeguts.RestaurantSearch.service;

import com.pobeguts.RestaurantSearch.model.VoteHistory;
import com.pobeguts.RestaurantSearch.repository.VoteHistoryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class VoteHistoryService {

    private  final VoteHistoryRepository voteHistoryRepository;
    private static final Logger log = getLogger(RestaurantService.class);

    @Autowired
    public VoteHistoryService(VoteHistoryRepository voteHistoryRepository) {
        this.voteHistoryRepository = voteHistoryRepository;
    }

    public List<VoteHistory> getAll() {
        return voteHistoryRepository.getAll();
    }
}
