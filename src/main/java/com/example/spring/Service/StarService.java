package com.example.spring.Service;

import com.example.spring.Entities.Star;
import com.example.spring.Repositories.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarService {

    @Autowired
    private StarRepository starRepository;

    public Star getStar(String s) {
        return starRepository.findByName(s);
    }

    public Star save(Star s){
       return starRepository.save(s);
    }
}
