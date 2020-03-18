package sdp.edu.java.recruitmentinformationanalysis.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sdp.edu.java.recruitmentinformationanalysis.pojo.User;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findUser(@Param("username")String username);

    @Select("select username from user")
    List<User> findAllUserName();

    @Insert("Insert into user (username,password) values (#{username},#{password})")
    void insertUserPwd(String username,String password);
}
