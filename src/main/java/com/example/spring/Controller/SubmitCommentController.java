package com.example.spring.Controller;

import com.example.spring.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class SubmitCommentController {

    @Autowired
    private SubmitCommentService submitCommentService;
    Logger logger = LoggerFactory.getLogger(SubmitCommentController.class);


    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    @ResponseBody
    public String submitComment(@RequestParam Map<String, String> fields) {
        logger.info("Submitting post" + fields);
        return submitCommentService.submitComment(fields);
    }
}
