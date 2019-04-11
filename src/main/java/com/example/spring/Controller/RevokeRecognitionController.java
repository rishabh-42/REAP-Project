package com.example.spring.Controller;

import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RevokeRecognitionController {


    @Autowired
    RevokeRecognitionService revokeRecognitionService;

    @PreAuthorize("hasAnyRole('Admin')")
    @PostMapping("/revoke")
    @ResponseBody
    public String revokeRecognition(@RequestParam Map<String,String> postData){
     return revokeRecognitionService.revokeRecognition(postData);
    }
}
