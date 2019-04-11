package com.example.spring.Controller;

import com.example.spring.Service.WriteToCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class CsvController {

   @Autowired
    WriteToCSVService writeToCSVService;
    @GetMapping(value = "/download")
    public void download(@RequestParam("startDate") String s, @RequestParam("endDate") String e, HttpServletResponse response) throws IOException, ParseException {
       writeToCSVService.writeCsv(s,e,response);
    }
}
