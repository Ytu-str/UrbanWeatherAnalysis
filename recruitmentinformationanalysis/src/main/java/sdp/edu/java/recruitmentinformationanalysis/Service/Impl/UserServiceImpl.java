package sdp.edu.java.recruitmentinformationanalysis.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.edu.java.recruitmentinformationanalysis.Mapper.UserMapper;
import sdp.edu.java.recruitmentinformationanalysis.Service.UserService;
import sdp.edu.java.recruitmentinformationanalysis.pojo.User;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public List<User> findAllUserName() {
        return userMapper.findAllUserName();
    }

    @Override
    public void insertUserPwd(String username,String password) {
        userMapper.insertUserPwd(username,password);
    }




}

