package sdp.edu.java.recruitmentinformationanalysis.Service.Impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.unReturnValue;
import sdp.edu.java.recruitmentinformationanalysis.Mapper.DistributionMapper;
import sdp.edu.java.recruitmentinformationanalysis.Service.DistributionService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("distributionService")
public class DistributionServiceImpl implements DistributionService {
    @Autowired
    private DistributionMapper distributionMapper;
    @Autowired
    private SolrClient solrClient;

    @unReturnValue
    @Override
    public Map<String, Object> NationalDistributionImpl() throws IOException, SolrServerException {
        Map<String, Object> map = new HashMap<>();
        //查询数量
        int num = distributionMapper.finmum();
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条数
        solrQuery.setRows(num);
        //设置查询的开始
        solrQuery.setStart(0);

        solrQuery.set("q", "workplace:北京*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsebeijing = solrClient.query(solrQuery);
        long beijijng = responsebeijing.getResults().getNumFound();
        map.put("beijing", beijijng);

        solrQuery.set("q", "workplace:广州*");
        solrQuery.set("fl", "workplace");
        QueryResponse responseguangzhou = solrClient.query(solrQuery);
        long guangzhou = responseguangzhou.getResults().getNumFound();
        map.put("guangzhou", guangzhou);

        solrQuery.set("q", "workplace:上海*");
        solrQuery.set("fl", "workplace");
        QueryResponse responseshanghai = solrClient.query(solrQuery);
        long shanghai = responseshanghai.getResults().getNumFound();
        map.put("shanghai", shanghai);

        solrQuery.set("q", "workplace:珠海*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsezhuhai = solrClient.query(solrQuery);
        long zhuhai = responsezhuhai.getResults().getNumFound();
        map.put("zhuhai", zhuhai);

        solrQuery.set("q", "workplace:佛山*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsefoshan = solrClient.query(solrQuery);
        long foshan = responsefoshan.getResults().getNumFound();
        map.put("foshan", foshan);

        solrQuery.set("q", "workplace:东莞*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsedongguan = solrClient.query(solrQuery);
        long dongguan = responsedongguan.getResults().getNumFound();
        map.put("dongguan", dongguan);

        solrQuery.set("q", "workplace:深圳*");
        solrQuery.set("fl", "workplace");
        QueryResponse responseshenzhen = solrClient.query(solrQuery);
        long shenzhen = responseshenzhen.getResults().getNumFound();
        map.put("shenzhen", shenzhen);

        solrQuery.set("q", "workplace:天津*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsetianjin = solrClient.query(solrQuery);
        long tianjin = responsetianjin.getResults().getNumFound();
        map.put("tianjin", tianjin);

        solrQuery.set("q", "workplace:重庆*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsechongqing = solrClient.query(solrQuery);
        long chongqing = responsechongqing.getResults().getNumFound();
        map.put("chongqing", chongqing);

        solrQuery.set("q", "workplace:南京*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsenanjing = solrClient.query(solrQuery);
        long nanjing = responsenanjing.getResults().getNumFound();
        map.put("nanjing", nanjing);

        solrQuery.set("q", "workplace:苏州*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsesuzhou = solrClient.query(solrQuery);
        long suzhou = responsesuzhou.getResults().getNumFound();
        map.put("suzhou", suzhou);

        solrQuery.set("q", "workplace:无锡*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsewuxi = solrClient.query(solrQuery);
        long wuxi = responsewuxi.getResults().getNumFound();
        map.put("wuxi", wuxi);

        solrQuery.set("q", "workplace:常州*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsechangzhou = solrClient.query(solrQuery);
        long changzhou = responsechangzhou.getResults().getNumFound();
        map.put("changzhou", changzhou);

        solrQuery.set("q", "workplace:杭州*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsehangzhou = solrClient.query(solrQuery);
        long hangzhou = responsehangzhou.getResults().getNumFound();
        map.put("hangzhou", hangzhou);

        solrQuery.set("q", "workplace:宁波*");
        solrQuery.set("fl", "workplace");
        QueryResponse responseningbo = solrClient.query(solrQuery);
        long ningbo = responseningbo.getResults().getNumFound();
        map.put("ningbo", ningbo);

        solrQuery.set("q", "workplace:成都*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsechengdu = solrClient.query(solrQuery);
        long chengdu = responsechengdu.getResults().getNumFound();
        map.put("chengdu", chengdu);

        solrQuery.set("q", "workplace:福州*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsefuzhou = solrClient.query(solrQuery);
        long fuzhou = responsefuzhou.getResults().getNumFound();
        map.put("fuzhou", fuzhou);

        solrQuery.set("q", "workplace:济南*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsejinan = solrClient.query(solrQuery);
        long jinan = responsejinan.getResults().getNumFound();
        map.put("jinan", jinan);

        solrQuery.set("q", "workplace:青岛*");
        solrQuery.set("fl", "workplace");
        QueryResponse responseqingdao = solrClient.query(solrQuery);
        long qingdao = responseqingdao.getResults().getNumFound();
        map.put("qingdao", qingdao);

        solrQuery.set("q", "workplace:南昌*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsenanchang = solrClient.query(solrQuery);
        long nanchang = responsenanchang.getResults().getNumFound();
        map.put("nanchang", nanchang);

        solrQuery.set("q", "workplace:合肥*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsehefei = solrClient.query(solrQuery);
        long hefei = responsehefei.getResults().getNumFound();
        map.put("hefei", hefei);

        solrQuery.set("q", "workplace:郑州*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsezhengzhou = solrClient.query(solrQuery);
        long zhengzhou = responsezhengzhou.getResults().getNumFound();
        map.put("zhengzhou", zhengzhou);

        solrQuery.set("q", "workplace:武汉*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsewuhan = solrClient.query(solrQuery);
        long wuhan = responsewuhan.getResults().getNumFound();
        map.put("wuhan", wuhan);

        solrQuery.set("q", "workplace:长沙*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsechangsha = solrClient.query(solrQuery);
        long changsha = responsechangsha.getResults().getNumFound();
        map.put("changsha", changsha);

        solrQuery.set("q", "workplace:西安*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsexian = solrClient.query(solrQuery);
        long xian = responsexian.getResults().getNumFound();
        map.put("xian", xian);

        solrQuery.set("q", "workplace:哈尔滨*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsehaerbin = solrClient.query(solrQuery);
        long haerbin = responsehaerbin.getResults().getNumFound();
        map.put("haerbin", haerbin);

        solrQuery.set("q", "workplace:沈阳*");
        solrQuery.set("fl", "workplace");
        QueryResponse responseshenyang = solrClient.query(solrQuery);
        long shenyang = responseshenyang.getResults().getNumFound();
        map.put("shenyang", shenyang);

        solrQuery.set("q", "workplace:大连*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsedalian = solrClient.query(solrQuery);
        long dalian = responsedalian.getResults().getNumFound();
        map.put("dalian", dalian);

        solrQuery.set("q", "workplace:长春*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsecahngchun = solrClient.query(solrQuery);
        long changchun = responsecahngchun.getResults().getNumFound();
        map.put("changchun", changchun);

        solrQuery.set("q", "workplace:昆明*");
        solrQuery.set("fl", "workplace");
        QueryResponse responsekunming = solrClient.query(solrQuery);
        long kunming = responsekunming.getResults().getNumFound();
        map.put("kunming", kunming);

        return map;
    }
}






