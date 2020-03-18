package sdp.edu.java.recruitmentinformationanalysis.Service;

import org.apache.solr.client.solrj.SolrServerException;
import sdp.edu.java.recruitmentinformationanalysis.vo.SalaryResult;

import java.io.IOException;


public interface SalaryService {

    SalaryResult Analysisone() throws IOException, SolrServerException;

    SalaryResult Analysisnewone() throws IOException, SolrServerException;

    SalaryResult Analysissecond() throws IOException, SolrServerException;
}
