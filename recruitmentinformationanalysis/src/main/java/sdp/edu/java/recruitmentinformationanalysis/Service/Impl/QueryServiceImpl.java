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
import sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.unReturnValue;
import sdp.edu.java.recruitmentinformationanalysis.Mapper.DistributionMapper;
import sdp.edu.java.recruitmentinformationanalysis.Service.QueryService;
import sdp.edu.java.recruitmentinformationanalysis.vo.Query;
import sdp.edu.java.recruitmentinformationanalysis.vo.QueryResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("queryService")
public class QueryServiceImpl implements QueryService {
    @Autowired
    private DistributionMapper distributionMapper;
    @Autowired
    private SolrClient solrClient;

    @unReturnValue
    @Override
    public List<Query> QueryRecruitmentInformation(String workplce,
                                                   String position,
                                                   String company,
                                                   int end,int start)
                                                            throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条数
        solrQuery.setRows(end);
        //设置查询的开始
        solrQuery.setStart(start);
        List<Query> list=new ArrayList();
        solrQuery.set("q", "workplace:" + workplce+"*");
        if (company != null) {
            solrQuery.set("fq", "position:" + position, "company:" + company);
        } else {
            solrQuery.set("fq", "position:" + position);
        }
        QueryResponse response = solrClient.query(solrQuery);
        if (response != null) {
            SolrDocumentList results = response.getResults();
            results.forEach(document->{
                Query query=new Query();
                query.setWorkplace((String) document.getFieldValue("workplace"));
                query.setPosition((String) document.getFieldValue("position"));
                query.setSalary((String) document.getFieldValue("salary"));
                query.setCompany((String) document.getFieldValue("company"));
                list.add(query);
            });
        }
        return list;
    }

    @Override
    public int findNum(int pageSize, int start) {
        return distributionMapper.finNum(pageSize,start);
    }
}
