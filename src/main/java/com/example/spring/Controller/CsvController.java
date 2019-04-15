package com.example.spring.Controller;

import com.example.spring.Service.WriteToCSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class CsvController {

    @Autowired
    private WriteToCSVService writeToCSVService;

    Logger logger = LoggerFactory.getLogger(CsvController.class);

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(@RequestParam("startDate") String s, @RequestParam("endDate") String e, HttpServletResponse response) throws IOException, ParseException {
        logger.info("Writing csv " +s+" "+ e+" " +response);
        writeToCSVService.writeCsv(s, e, response);
    }
}
