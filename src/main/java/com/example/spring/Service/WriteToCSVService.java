package com.example.spring.Service;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.utils.writecsv.WriteDataToCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WriteToCSVService {
    @Autowired
    BadgesGivenService badgesGivenService;
    public void writeCsv(String startDate, String endDate, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; file=BadgesGiven.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(endDate, formatter);
        List<BadgesGiven> badgesGivens = badgesGivenService.findBetweenDate(dateStart,dateEnd).stream().filter(f->f.isActive()==true).collect(Collectors.toList());
        WriteDataToCsv.writeObjectToCSV(response.getWriter(), badgesGivens);
    }


}
