package sdp.edu.java.recruitmentinformationanalysis.Service;

import org.apache.solr.client.solrj.SolrServerException;
import sdp.edu.java.recruitmentinformationanalysis.vo.SalaryResult;

import java.io.IOException;

public interface QuerySalaryStatusService {

    SalaryResult SalarySituation(String workplace) throws IOException, SolrServerException;

}
