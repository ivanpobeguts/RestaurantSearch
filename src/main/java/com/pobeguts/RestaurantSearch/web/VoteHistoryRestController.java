package com.pobeguts.RestaurantSearch.web;

import com.pobeguts.RestaurantSearch.model.VoteHistory;
import com.pobeguts.RestaurantSearch.service.VoteHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = VoteHistoryRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteHistoryRestController {

    static final String REST_URL = "/rest/history";

    private final VoteHistoryService voteHistoryService;

    @Autowired
    public VoteHistoryRestController(VoteHistoryService voteHistoryService) {
        this.voteHistoryService = voteHistoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<VoteHistory> getVoteHistory() {
        return voteHistoryService.getAll();
    }
}
