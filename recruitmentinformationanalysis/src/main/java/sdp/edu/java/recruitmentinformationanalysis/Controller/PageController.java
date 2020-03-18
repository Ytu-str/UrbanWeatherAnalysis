package sdp.edu.java.recruitmentinformationanalysis.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("homepage")
    public String homepage(){
        return "homepage";
    }
    @GetMapping("selectbox")
    public String selectbox(){
        return "selectbox";
    }
    @GetMapping("mapmap")
    public String mapmap(){
        return "mapmap";
    }
    @GetMapping("map")
    public String map(){
        return "map03";
    }
    @GetMapping("one")
    public String one(){
        return "fenbu";
    }
    @GetMapping("two")
    public String two(){
        return "222";
    }
    @GetMapping("dudu")
    public String dudu(){
        return "dudu";
    }
}
