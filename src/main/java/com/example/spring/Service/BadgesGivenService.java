package com.example.spring.Service;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Repositories.BadgesGivenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgesGivenService {
    @Autowired
    private BadgesGivenRepository badgesGivenRepository;

    public void save(BadgesGiven badgesGiven) {
        badgesGivenRepository.save(badgesGiven);
    }

    public List<BadgesGiven> getAllPosts() {
        return badgesGivenRepository.findByActiveOrderByIdDesc(true);
    }


    public List<BadgesGiven> getListOfGiver(User user) {
        return badgesGivenRepository.findByGiverAndActive(user, true);
    }

    public List<BadgesGiven> getListOfReciever(User user) {
        return badgesGivenRepository.findByReceiverAndActive(user, true);
    }


    public BadgesGiven findById(int id) {
        return badgesGivenRepository.findById(id).get();
    }

    public List<BadgesGiven> findBetweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        return badgesGivenRepository.findAllByCreateDateTimeBetween(startDate, endDate);
    }

    public List<BadgesGiven> findByGiverOrReciever(User user1, User user2) {
        return badgesGivenRepository.findAllByGiverOrReceiverOrderByIdDesc(user1, user2).stream().filter(e -> e.isActive() == true).collect(Collectors.toList());
    }
}
