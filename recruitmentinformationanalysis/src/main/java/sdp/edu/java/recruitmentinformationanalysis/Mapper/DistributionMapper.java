package sdp.edu.java.recruitmentinformationanalysis.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface DistributionMapper {

    @Select("select count(*) from information")
    int finmum();


    //查询数据数量
    @Select("select count(1) from information")
    int finNum(int pageSize, int start);
}
