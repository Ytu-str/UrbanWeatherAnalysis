package sdp.edu.java.recruitmentinformationanalysis.Service.Impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.ReturnValue;
import sdp.edu.java.recruitmentinformationanalysis.vo.Salary;
import sdp.edu.java.recruitmentinformationanalysis.vo.SalaryResult;
import sdp.edu.java.recruitmentinformationanalysis.Mapper.DistributionMapper;
import sdp.edu.java.recruitmentinformationanalysis.Service.QuerySalaryStatusService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service("querySalaryStatusService")
public class QuerySalaryStatusServiceImpl implements QuerySalaryStatusService {
    @Autowired
    private DistributionMapper distributionMapper;
    @Autowired
    private SolrClient solrClient;

    @ReturnValue
    @Override
    public SalaryResult SalarySituation(String workplace) throws IOException, SolrServerException {
        SalaryResult salaryResult =new SalaryResult();
        List<Salary> list=new ArrayList<>();
        int num = distributionMapper.finmum();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRows(num);   //设置查询条数
        solrQuery.setStart(0);//设置查询的开始
        long MaxTotal = 0;//最大值总数
        long MinTotal = 0;//最小值总数
        long count = 0;//计数
        solrQuery.set("q", "workplace:" + workplace + "*");
        solrQuery.set("fl", "salary");
        QueryResponse response = solrClient.query(solrQuery);
        if (response != null) {
            SolrDocumentList results = response.getResults();
            for (SolrDocument document : results) {
                String min = (String) document.getFieldValue("salary");
                if (min == null) {
                    continue;
                }
                String[] strMin = min.split("-");
                String nummin = strMin[0];
                String nummax = strMin[1];
                int Min = Integer.parseInt(nummin);
                int Max = Integer.parseInt(nummax);
                MinTotal =  Min + MinTotal;
                MaxTotal =  Max + MaxTotal;
                count = count + 1;
            }
            long Min = MinTotal / count;
            long Max = MaxTotal / count;
            long Average=(Max+Min)/2;
            Salary salary=new Salary();
            salary.setMin(Min);
            salary.setMax(Max);
            salary.setAvg(Average);
            list.add(salary);
            salaryResult.setSalarylist(list);
        }
        return salaryResult;
    }

}
