package com.ssd.synchronizer.Controller;

import com.ssd.synchronizer.Service.SynchronizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSRFController {

    @Autowired
    SynchronizerService synchronizerService;

    @GetMapping("/get-token")
    public ResponseEntity<String> getCSRFToken(){

        System.out.println(synchronizerService.getCSRFServerToken());
        return ResponseEntity.ok(synchronizerService.getCSRFServerToken());

    }
}
