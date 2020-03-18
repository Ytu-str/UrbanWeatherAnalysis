package sdp.edu.java.recruitmentinformationanalysis.Service.Impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.unReturnValue;
import sdp.edu.java.recruitmentinformationanalysis.Mapper.DistributionMapper;
import sdp.edu.java.recruitmentinformationanalysis.Service.StageSalaryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("stageSalaryService")
public class StageSalaryServiceImpl implements StageSalaryService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private DistributionMapper distributionMapper;

    @unReturnValue
    @Override
    public Map<String,Object> CityStageSalary(String workplace) throws IOException, SolrServerException {
        Map<String, Object> map = new HashMap<>();
        int num=distributionMapper.finmum();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRows(num);  //设置查询条数
        solrQuery.setStart(0);//设置查询的开始
        List list1=new ArrayList();
        List list2=new ArrayList();
        List list3=new ArrayList();
        List list4=new ArrayList();
        List list5=new ArrayList();
        List list6=new ArrayList();
        List list7=new ArrayList();
        List list8=new ArrayList();
        solrQuery.set("q", "workplace:"+workplace+"*");
        QueryResponse response = solrClient.query(solrQuery);
        if (response != null) {
            SolrDocumentList results = response.getResults();
            results.forEach(document->{
                String min = (String) document.getFieldValue("salary");
                String id= (String) document.getFieldValue("id");
                String[] strMin = min.split("-");
                String nummin = strMin[0];
                String nummax = strMin[1];
                int Min = Integer.parseInt(nummin);
                int Max = Integer.parseInt(nummax);
                int AverageValue=(Min+Max)/2;
                if(AverageValue>0&&AverageValue<3000){
                    list1.add(id);
                } else if(AverageValue>=3000&&AverageValue<=5000){
                    list2.add(id);
                }else if(AverageValue>5000&&AverageValue<=7000){
                    list3.add(id);
                }else if(AverageValue>7000&&AverageValue<=9000){
                    list4.add(id);
                }else if(AverageValue>9000&&AverageValue<=12000){
                    list5.add(id);
                }else if(AverageValue>12000&&AverageValue<=15000){
                    list6.add(id);
                }else if(AverageValue>15000&&AverageValue<=20000){
                    list7.add(id);
                }else if(AverageValue>20000){
                    list8.add(id);
                }
            });
            map.put("three",list1);
            map.put("threetofive",list2);
            map.put("fivetoseven",list3);
            map.put("seventonine",list4);
            map.put("ninetowelve",list5);
            map.put("twelvetofifteen",list6);
            map.put("fifteentotwenty",list7);
            map.put("twenty",list8);
        }
        return map;
    }
}
