package com.rcd.iotsubsys.web.rest.warn;

import com.codahale.metrics.annotation.Timed;
import com.rcd.iotsubsys.domain.event.ComplexEvent;
import com.rcd.iotsubsys.domain.warn.Warn;
import com.rcd.iotsubsys.service.warn.WarnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/warn/warn")
public class WarnResource {
    Logger logger = LoggerFactory.getLogger(WarnResource.class);
    private final WarnService warnService;

    public WarnResource(WarnService warnService) {
        this.warnService = warnService;
    }

    @GetMapping()
    @Timed
    public ResponseEntity<Warn> getWarn() {

        Warn warn = warnService.getType();
        logger.info(warn.getType()+"-----------"+warn.getId());

        if (warn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(warn);
    }

    //新增或修改
    @PostMapping("/saveWarn")
    @Timed
    public ResponseEntity<Warn> saveWarn(@RequestBody String type) {

        Warn result = warnService.save(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    public static void main(String[] args) {
//        try {
////            Runtime.getRuntime().exec("C:\\Users\\hyf_0\\Desktop\\this\\z3exe\\z3.exe");
//            Runtime.getRuntime().exec("cmd /c \"C:\\Users\\hyf_0\\Desktop\\this\\z3exe\\z3.exe\"");
//            System.out.println("111");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            Desktop.getDesktop().open(new File("C:\\Users\\hyf_0\\Desktop\\this\\z3exe\\z3.exe"));
//            Thread.sleep(85500);
//            System.out.println("123123123");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }   catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
