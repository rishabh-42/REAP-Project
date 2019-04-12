package com.example.spring.Controller;

import com.example.spring.Service.*;
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

    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    @ResponseBody
    public String submitComment(@RequestParam Map<String, String> fields) {
        return submitCommentService.submitComment(fields);
    }
}
