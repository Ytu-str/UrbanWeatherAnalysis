package sdp.edu.java.recruitmentinformationanalysis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.ReturnValue;
import sdp.edu.java.recruitmentinformationanalysis.Service.UserService;
import sdp.edu.java.recruitmentinformationanalysis.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public  String hello(){
        return "hello";
    }

    @GetMapping("Signin")
    public String SignIn(){
        return "index";
    }
    @GetMapping(value = "register" )
    public String reg(){
        return "register";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @ReturnValue
    @ResponseBody
    @PostMapping("/Signin")
    public int Sign(@RequestParam(value = "username")String username,
                    @RequestParam(value = "password")String password,
                    HttpSession session) {
        int result;
        //查询数据库中已经存在的用户名密码
        try {
            User user = userService.findUser(username);
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
                session.setAttribute("SESSION_LOGIN",user);
                result=1;
            }else if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)==false) {
                //密码错误
                result=2;
            }else {
                result=2;
            }
        }catch (Exception e){
            result=2;
        }
        return result;

    }
    /**
     * 注册
     */
    @ReturnValue
    @ResponseBody
    @PostMapping(value = "register")
    public int register(@RequestParam(value = "username")String username ,
                        @RequestParam(value = "password")String password) {
        User user =new User();
        user.setUsername(username);
        user.setPassword(password);
        int result = 1;
        List<User> u1 = userService.findAllUserName();  //获取所有用户名
        for (int i = 0; i < u1.size(); i++) { //遍历用户名跟获取到的用户名比较
            String a=u1.get(i).getUsername();
            if (a.equals(user.getUsername())) {
                result = 0;   //用户名已经被注册
            }
        }
        if (user.getPassword().equals("")) {
            result = 2;    //密码不能为空
        } else if (user.getUsername().equals("")) {
            result = 3;    //账号不能为空
        }
        if (result == 1) {
            userService.insertUserPwd(username,password);
        }
        return result;
    }
}
