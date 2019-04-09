package com.example.spring.Controller;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Service.BadgesGivenService;
import com.example.spring.others.WriteDataToCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CsvController {

    @Autowired
    BadgesGivenService badgesGivenService;
    @GetMapping(value = "/download")
    public void download(@RequestParam("startDate") String s, @RequestParam("endDate") String e, HttpServletResponse response) throws IOException, ParseException {
        response.setHeader("Content-Disposition", "attachment; file=BadgesGiven.csv");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(s, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(e, formatter);



        System.out.println(dateEnd);
        System.out.println(dateStart);
        List<BadgesGiven> badgesGivens = badgesGivenService.findBetweenDate(dateStart,dateEnd).stream().filter(f->f.isActive()==true).collect(Collectors.toList());

        WriteDataToCsv.writeObjectToCSV(response.getWriter(), badgesGivens);
    }
}
