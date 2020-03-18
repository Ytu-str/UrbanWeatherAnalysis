package sdp.edu.java.recruitmentinformationanalysis.Service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

public interface StageSalaryService {

    Map<String,Object> CityStageSalary(String workplace) throws IOException, SolrServerException;

}
