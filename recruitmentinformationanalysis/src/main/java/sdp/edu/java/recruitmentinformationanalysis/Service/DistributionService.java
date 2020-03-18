package sdp.edu.java.recruitmentinformationanalysis.Service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

public interface DistributionService {

    Map<String, Object> NationalDistributionImpl() throws IOException, SolrServerException;


}
