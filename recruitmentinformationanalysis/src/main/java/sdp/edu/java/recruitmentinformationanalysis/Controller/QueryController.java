package sdp.edu.java.recruitmentinformationanalysis.Controller;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdp.edu.java.recruitmentinformationanalysis.Service.QueryService;
import sdp.edu.java.recruitmentinformationanalysis.vo.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class QueryController {

    @Autowired
    private QueryService queryService;

    /**
     * 按条件查询
     * @param workplace
     * @param position
     * @param company
     * @return
     * @throws Exception
     */
    @GetMapping("Query")
    @ResponseBody
    public Map<String,Object> Query(@RequestParam(value = "workplace")String workplace,
                                    @RequestParam(value = "position")String position,
                                    @RequestParam(value = "company",required = false)String company,
                                    @RequestParam(value="page")String page,
                                    @RequestParam(value="limit")String limit)throws Exception{
        int pageSize=Integer.parseInt(limit);
        int pageNow=Integer.parseInt(page);
        int start = (pageNow - 1) * pageSize;
        int end = pageNow;
        int num=queryService.findNum(start,pageSize);
        Map<String,Object> map=new HashMap<>();
        List<Query> queryResult=queryService.QueryRecruitmentInformation(workplace,position,company,end,start);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count",num);
        JSONArray array = JSONArray.fromObject(queryResult);
        map.put("data",array);
        return map;
    }
}
