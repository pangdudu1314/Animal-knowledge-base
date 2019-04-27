package com.li.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/queryClass")
public class SelectController {
    @RequestMapping("/gotoFrame")
    public String gotoFrame() {
        return "frame";
    }

    @RequestMapping("/frame_a")
    public String frame_a()  {
        return "frame_a";
    }

    @RequestMapping("/frame_b")
    public String frame_b()  {
        return "frame_b";
    }

    @RequestMapping("/wenzi")
    public String wenzi()  {
        return "wenzichaxun";
    }

    @RequestMapping("/tupian")
    public String  tupian()  {
        return "tupianchaxun";
    }

}
