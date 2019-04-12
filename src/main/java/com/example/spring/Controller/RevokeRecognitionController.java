package com.example.spring.Controller;

import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RevokeRecognitionController {


    @Autowired
    private RevokeRecognitionService revokeRecognitionService;

    @PreAuthorize("hasAnyRole('Admin')")
    @RequestMapping(value = "/revoke",method = RequestMethod.POST)
    @ResponseBody
    public String revokeRecognition(@RequestParam Map<String, String> postData) {
        return revokeRecognitionService.revokeRecognition(postData);
    }
}
