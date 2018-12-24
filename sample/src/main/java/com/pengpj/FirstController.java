package com.pengpj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * @author pengpj
 * @date 2018/12/24
 */
@RequestMapping("/")
@RestController
public class FirstController {

    @GetMapping("/now")
    public Object now() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
