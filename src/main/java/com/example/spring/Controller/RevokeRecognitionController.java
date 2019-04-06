package com.example.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RevokeRecognitionController {

    @PostMapping("/revoke")
    @ResponseBody
    public String revokeRecognition(@RequestParam Map<String,String> postData){


        System.out.println(postData);
        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "done";

    }
}
