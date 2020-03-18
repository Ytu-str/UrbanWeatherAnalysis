package sdp.edu.java.recruitmentinformationanalysis.Service;

import org.apache.solr.client.solrj.SolrServerException;
import sdp.edu.java.recruitmentinformationanalysis.vo.Query;
import sdp.edu.java.recruitmentinformationanalysis.vo.QueryResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface QueryService {

    List<Query> QueryRecruitmentInformation(String workplce, String position, String company, int end, int start) throws IOException, SolrServerException;

    int findNum(int pageSize, int start);


}
