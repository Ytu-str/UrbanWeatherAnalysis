package sdp.edu.java.recruitmentinformationanalysis.Service;

import sdp.edu.java.recruitmentinformationanalysis.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAllUserName();

    void insertUserPwd(String username, String password);

    User findUser(String username);

}
