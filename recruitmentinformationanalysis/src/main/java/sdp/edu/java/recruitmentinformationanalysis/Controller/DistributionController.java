package sdp.edu.java.recruitmentinformationanalysis.Controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.unReturnValue;
import sdp.edu.java.recruitmentinformationanalysis.vo.SalaryResult;
import sdp.edu.java.recruitmentinformationanalysis.Service.DistributionService;
import sdp.edu.java.recruitmentinformationanalysis.Service.QuerySalaryStatusService;
import sdp.edu.java.recruitmentinformationanalysis.Service.SalaryService;
import sdp.edu.java.recruitmentinformationanalysis.Service.StageSalaryService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DistributionController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private StageSalaryService stageSalaryService;
    @Autowired
    private QuerySalaryStatusService querySalaryStatusService;
    /**
     * 全国分布
     * @return
     */
    @PostMapping("findAllWorkplace")
    @ResponseBody
    public Map<String, Object> NationalDistribution() throws IOException, SolrServerException {
        Map<String, Object> map= distributionService.NationalDistributionImpl();
        return map;
    }

    /**
     *城市阶段薪资
     */
    @PostMapping("findAloneStage")
    @ResponseBody
    public Map<String,Object> CityStageSalary(@RequestParam(value = "workplace")String workplace) throws IOException, SolrServerException {
        Map<String,Object> map =stageSalaryService.CityStageSalary(workplace);
        return map;
    }

    /**
     * 城市薪资情况
     */
    @GetMapping("findAloneSalarySituation")
    @ResponseBody
    public SalaryResult SalarySituation(@RequestParam(value = "workplace")String workplace) throws IOException, SolrServerException {
        SalaryResult salaryResult =querySalaryStatusService.SalarySituation(workplace);
        return salaryResult;
    }

    /**
     * 城市级别薪资比较
     * @return
     */
    @unReturnValue
    @PostMapping("findAllSalary")
    @ResponseBody
    public Map<String, SalaryResult> Distribution() throws IOException, SolrServerException {
        Map map=new HashMap<>();
        SalaryResult salaryResult1 = salaryService.Analysisone();
        SalaryResult salaryResult2= salaryService.Analysisnewone();
        SalaryResult salaryResult3= salaryService.Analysissecond();
        map.put("frist",salaryResult1);
        map.put("newfrist",salaryResult2);
        map.put("second",salaryResult3);
        return map;
    }
}
