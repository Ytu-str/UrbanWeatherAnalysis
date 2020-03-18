package sdp.edu.java.recruitmentinformationanalysis.Service.Impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.edu.java.recruitmentinformationanalysis.vo.Salary;
import sdp.edu.java.recruitmentinformationanalysis.vo.SalaryResult;
import sdp.edu.java.recruitmentinformationanalysis.Mapper.DistributionMapper;
import sdp.edu.java.recruitmentinformationanalysis.Service.SalaryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service("salaryService")
public class SalayServiceImpl implements SalaryService {
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private DistributionMapper distributionMapper;


    @Override
    public SalaryResult Analysisone() throws IOException, SolrServerException {
        SalaryResult salaryResult=new SalaryResult();
        List<Salary> list=new ArrayList<>();
        int num = distributionMapper.finmum();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRows(num);   //设置查询条数
        solrQuery.setStart(0);//设置查询的开始
        long MaxTotal = 0;//最大值总数
        long MinTotal = 0;//最小值总数
        long count = 0;//计数
        //一线城市
        solrQuery.set("q", "workplace:北京*");
        solrQuery.set("fl", "salary");
        QueryResponse responsebeijing = solrClient.query(solrQuery);
        if (responsebeijing != null) {
            SolrDocumentList resultsbeijing = responsebeijing.getResults();
           for (SolrDocument document : resultsbeijing) {
                String min = (String) document.getFieldValue("salary");
                if (min == null) {
                    continue;
                }
                String[] strMin = min.split("-");
                String nummin = strMin[0];
                String nummax = strMin[1];
                int Min = Integer.parseInt(nummin);
                int Max = Integer.parseInt(nummax);
                MinTotal = Min + MinTotal;
                MaxTotal = Max + MaxTotal;
                count = count + 1;
            }
            long beijingMin = MinTotal / count;
            long beijingMax = MaxTotal / count;


            solrQuery.set("q", "workplace:广州*");
            solrQuery.set("fl", "salary");
            QueryResponse responseguangzhou = solrClient.query(solrQuery);
            if (responseguangzhou != null) {
                SolrDocumentList resultsguangzhou = responseguangzhou.getResults();
                for (SolrDocument document : resultsguangzhou) {
                    String min = (String) document.getFieldValue("salary");
                    if (min == null) {
                        continue;
                    }
                    String[] strMin = min.split("-");
                    String nummin = strMin[0];
                    String nummax = strMin[1];
                    int Min = Integer.parseInt(nummin);
                    int Max = Integer.parseInt(nummax);
                    MaxTotal = (Max + MaxTotal);
                    MinTotal = (Min + MinTotal);
                    count = count + 1;
                }

                long guangzhouMin = MinTotal / count;
                long guangzhouMax = MaxTotal / count;


                solrQuery.set("q", "workplace:上海*");
                solrQuery.set("fl", "salary");
                QueryResponse responseshanghai = solrClient.query(solrQuery);
                if (responseshanghai != null) {
                    SolrDocumentList resultsshanghai = responseshanghai.getResults();
                    for (SolrDocument document : resultsshanghai) {
                        String min = (String) document.getFieldValue("salary");
                        if (min == null) {
                            continue;
                        }
                        String[] strMin = min.split("-");
                        String nummin = strMin[0];
                        String nummax = strMin[1];
                        int Min = Integer.parseInt(nummin);
                        int Max = Integer.parseInt(nummax);
                        MaxTotal = Max + MaxTotal;
                        MinTotal = Min + MinTotal;
                        count = count + 1;
                    }

                    long shanghaiMin = MinTotal / count;
                    long shanghaiMax = MaxTotal / count;

                    solrQuery.set("q", "workplace:深圳*");
                    solrQuery.set("fl", "salary");
                    QueryResponse responseshenzhen = solrClient.query(solrQuery);
                    if (responseshenzhen != null) {
                        SolrDocumentList resultsshenzhen = responseshenzhen.getResults();
                        for (SolrDocument document : resultsshenzhen) {
                            String min = (String) document.getFieldValue("salary");
                            if (min == null) {
                                continue;
                            }
                            String[] strMin = min.split("-");
                            String nummin = strMin[0];
                            String nummax = strMin[1];
                            int Min = Integer.parseInt(nummin);
                            int Max = Integer.parseInt(nummax);
                            MaxTotal = Max + MaxTotal;
                            MinTotal = Min + MinTotal;
                            count = count + 1;
                        }

                        long shenzhenMin = MinTotal / count;
                        long shenzhenMax = MaxTotal / count;
                        //一线城市数据
                        long OneNumMin = (shenzhenMin + shanghaiMin + guangzhouMin + beijingMin) / 4;
                        long OneNumMax = (shenzhenMax + shanghaiMax + guangzhouMax + beijingMax) / 4;
                        long OneNumAverage = (OneNumMax + OneNumMin) / 2;
                        Salary salary=new Salary();
                        salary.setMax(OneNumMax);
                        salary.setMin(OneNumMin);
                        salary.setAvg(OneNumAverage);
                        list.add(salary);
                        salaryResult.setSalarylist(list);
                    }
                }
            }
        }
        return salaryResult;
    }

    @Override
    public SalaryResult Analysisnewone() throws IOException, SolrServerException {
        SalaryResult salaryResult=new SalaryResult();
        List<Salary> list=new ArrayList<>();
        int num = distributionMapper.finmum();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRows(num);   //设置查询条数
        solrQuery.setStart(0);//设置查询的开始
        long MaxTotal = 0;//最大值总数
        long MinTotal = 0;//最小值总数
        long count = 0;//计数
        //新一线城市
        solrQuery.set("q", "workplace:天津*");
        solrQuery.set("fl", "salary");
        QueryResponse responsetianjin = solrClient.query(solrQuery);
        if (responsetianjin != null) {
            SolrDocumentList resultstianjin = responsetianjin.getResults();
            for (SolrDocument document : resultstianjin) {
                String min = (String) document.getFieldValue("salary");
                if (min == null) {
                    continue;
                }
                String[] strMin = min.split("-");
                String nummin = strMin[0];
                String nummax = strMin[1];
                int Min = Integer.parseInt(nummin);
                int Max = Integer.parseInt(nummax);
                MaxTotal = Max + MaxTotal;
                MinTotal = Min + MinTotal;
                count = count + 1;
            }
                long tianjinMin = MinTotal / count;
                long tianjinMax = MaxTotal / count;

            solrQuery.set("q", "workplace:重庆*");
            solrQuery.set("fl", "salary");
            QueryResponse responsechongqing = solrClient.query(solrQuery);
            if (responsechongqing != null) {
                SolrDocumentList resultschongqing = responsechongqing.getResults();
                for (SolrDocument document : resultschongqing) {
                    String min = (String) document.getFieldValue("salary");
                    if (min == null) {
                        continue;
                    }
                    String[] strMin = min.split("-");
                    String nummin = strMin[0];
                    String nummax = strMin[1];
                    int Min = Integer.parseInt(nummin);
                    int Max = Integer.parseInt(nummax);
                    MaxTotal = Max + MaxTotal;
                    MinTotal = Min + MinTotal;
                    count = count + 1;
                }

                long chongqingMin = MinTotal / count;
                long chongqingMax = MaxTotal / count;

                solrQuery.set("q", "workplace:南京*");
                solrQuery.set("fl", "salary");
                QueryResponse responsenanjing = solrClient.query(solrQuery);
                if (responsenanjing != null) {
                    SolrDocumentList resultsnanjing = responsenanjing.getResults();
                    for (SolrDocument document : resultsnanjing) {
                        String min = (String) document.getFieldValue("salary");
                        if (min == null) {
                            continue;
                        }
                        String[] strMin = min.split("-");
                        String nummin = strMin[0];
                        String nummax = strMin[1];
                        int Min = Integer.parseInt(nummin);
                        int Max = Integer.parseInt(nummax);
                        MaxTotal = Max + MaxTotal;
                        MinTotal = Min + MinTotal;
                        count = count + 1;
                    }

                    long nanjingMin = MinTotal / count;
                    long nanjingMax = MaxTotal / count;

                    solrQuery.set("q", "workplace:苏州*");
                    solrQuery.set("fl", "salary");
                    QueryResponse responsesuzhou = solrClient.query(solrQuery);
                    if (responsesuzhou != null) {
                        SolrDocumentList resultssuzhou = responsesuzhou.getResults();
                        for (SolrDocument document : resultssuzhou) {
                            String min = (String) document.getFieldValue("salary");
                            if (min == null) {
                                continue;
                            }
                            String[] strMin = min.split("-");
                            String nummin = strMin[0];
                            String nummax = strMin[1];
                            int Min = Integer.parseInt(nummin);
                            int Max = Integer.parseInt(nummax);
                            MaxTotal = Max + MaxTotal;
                            MinTotal = Min + MinTotal;
                            count = count + 1;
                        }

                        long suzhouMin = MinTotal / count;
                        long suzhouMax = MaxTotal / count;

                        solrQuery.set("q", "workplace:杭州*");
                        solrQuery.set("fl", "salary");
                        QueryResponse responsehangzhou = solrClient.query(solrQuery);
                        if (responsehangzhou != null) {
                            SolrDocumentList resultshangzhou = responsehangzhou.getResults();
                            for (SolrDocument document : resultshangzhou) {
                                String min = (String) document.getFieldValue("salary");
                                if (min == null) {
                                    continue;
                                }
                                String[] strMin = min.split("-");
                                String nummin = strMin[0];
                                String nummax = strMin[1];
                                int Min = Integer.parseInt(nummin);
                                int Max = Integer.parseInt(nummax);
                                MaxTotal = Max + MaxTotal;
                                MinTotal = Min + MinTotal;
                                count = count + 1;
                            }

                            long hangzhouMin = MinTotal / count;
                            long hangzhouMax = MaxTotal / count;

                            solrQuery.set("q", "workplace:宁波*");
                            solrQuery.set("fl", "Salary");
                            QueryResponse responseningbo = solrClient.query(solrQuery);
                            if (responseningbo != null) {
                                SolrDocumentList resultningbo = responseningbo.getResults();
                                for (SolrDocument document : resultningbo) {
                                    String min = (String) document.getFieldValue("salary");
                                    if (min == null) {
                                        continue;
                                    }
                                    String[] strMin = min.split("-");
                                    String nummin = strMin[0];
                                    String nummax = strMin[1];
                                    int Min = Integer.parseInt(nummin);
                                    int Max = Integer.parseInt(nummax);
                                    MaxTotal = Max + MaxTotal;
                                    MinTotal = Min + MinTotal;
                                    count = count + 1;
                                }

                                long ningboMin = MinTotal / count;
                                long ningboMax = MaxTotal / count;

                                solrQuery.set("q", "workplace:成都*");
                                solrQuery.set("fl", "salary");
                                QueryResponse responsechengdu = solrClient.query(solrQuery);
                                if (responsechengdu != null) {
                                    SolrDocumentList resultchengdu = responsechengdu.getResults();
                                    for (SolrDocument document : resultchengdu) {
                                        String min = (String) document.getFieldValue("salary");
                                        if (min == null) {
                                            continue;
                                        }
                                        String[] strMin = min.split("-");
                                        String nummin = strMin[0];
                                        String nummax = strMin[1];
                                        int Min = Integer.parseInt(nummin);
                                        int Max = Integer.parseInt(nummax);
                                        MaxTotal = Max + MaxTotal;
                                        MinTotal = Min + MinTotal;
                                        count = count + 1;
                                    }

                                    long chengduMin = MinTotal / count;
                                    long chengduMax = MaxTotal / count;

                                    solrQuery.set("q", "workplace:青岛*");
                                    solrQuery.set("fl", "salary");
                                    QueryResponse responseqingdao = solrClient.query(solrQuery);
                                    if (responseqingdao != null) {
                                        SolrDocumentList resultqingdao = responseqingdao.getResults();
                                        for (SolrDocument document : resultqingdao) {
                                            String min = (String) document.getFieldValue("salary");
                                            if (min == null) {
                                                continue;
                                            }
                                            String[] strMin = min.split("-");
                                            String nummin = strMin[0];
                                            String nummax = strMin[1];
                                            int Min = Integer.parseInt(nummin);
                                            int Max = Integer.parseInt(nummax);
                                            MaxTotal = Max + MaxTotal;
                                            MinTotal = Min + MinTotal;
                                            count = count + 1;
                                        }

                                        long qingdaoMin = MinTotal / count;
                                        long qingdaoMax = MaxTotal / count;

                                        solrQuery.set("q", "workplace:郑州*");
                                        solrQuery.set("fl", "salary");
                                        QueryResponse responsezhengzhou = solrClient.query(solrQuery);
                                        if (responsezhengzhou != null) {
                                            SolrDocumentList resultzhengzhou = responsezhengzhou.getResults();
                                            for (SolrDocument document : resultzhengzhou) {
                                                String min = (String) document.getFieldValue("salary");
                                                if (min == null) {
                                                    continue;
                                                }
                                                String[] strMin = min.split("-");
                                                String nummin = strMin[0];
                                                String nummax = strMin[1];
                                                int Min = Integer.parseInt(nummin);
                                                int Max = Integer.parseInt(nummax);
                                                MaxTotal = Max + MaxTotal;
                                                MinTotal = Min + MinTotal;
                                                count = count + 1;
                                            }

                                            long zhengzhouMin = MinTotal / count;
                                            long zhengzhouMax = MaxTotal / count;

                                            solrQuery.set("q", "workplace:武汉*");
                                            solrQuery.set("fl", "salary");
                                            QueryResponse responsewuhan = solrClient.query(solrQuery);
                                            if (responsewuhan != null) {
                                                SolrDocumentList resultwuhan = responsewuhan.getResults();
                                                for (SolrDocument document : resultwuhan) {
                                                    String min = (String) document.getFieldValue("salary");
                                                    if (min == null) {
                                                        continue;
                                                    }
                                                    String[] strMin = min.split("-");
                                                    String nummin = strMin[0];
                                                    String nummax = strMin[1];
                                                    int Min = Integer.parseInt(nummin);
                                                    int Max = Integer.parseInt(nummax);
                                                    MaxTotal = Max + MaxTotal;
                                                    MinTotal = Min + MinTotal;
                                                    count = count + 1;
                                                }

                                                long wuhanMin = MinTotal / count;
                                                long wuhanMax = MaxTotal / count;

                                                solrQuery.set("q", "workplace:长沙*");
                                                solrQuery.set("fl", "salary");
                                                QueryResponse responsechangsha = solrClient.query(solrQuery);
                                                if (responsechangsha != null) {
                                                    SolrDocumentList resultchangsha = responsechangsha.getResults();
                                                    for (SolrDocument document : resultchangsha) {
                                                        String min = (String) document.getFieldValue("salary");
                                                        if (min == null) {
                                                            continue;
                                                        }
                                                        String[] strMin = min.split("-");
                                                        String nummin = strMin[0];
                                                        String nummax = strMin[1];
                                                        int Min = Integer.parseInt(nummin);
                                                        int Max = Integer.parseInt(nummax);
                                                        MaxTotal = Max + MaxTotal;
                                                        MinTotal = Min + MinTotal;
                                                        count = count + 1;
                                                    }

                                                    long changshaMin = MinTotal / count;
                                                    long changshaMax = MaxTotal / count;

                                                    solrQuery.set("q", "workplace:西安*");
                                                    solrQuery.set("fl", "salary");
                                                    QueryResponse responsexian = solrClient.query(solrQuery);
                                                    if (responsexian != null) {
                                                        SolrDocumentList resultxian = responsexian.getResults();
                                                        for (SolrDocument document : resultxian) {
                                                            String min = (String) document.getFieldValue("salary");
                                                            if (min == null) {
                                                                continue;
                                                            }
                                                            String[] strMin = min.split("-");
                                                            String nummin = strMin[0];
                                                            String nummax = strMin[1];

                                                            int Min = Integer.parseInt(nummin);
                                                            int Max = Integer.parseInt(nummax);
                                                            MaxTotal = Max + MaxTotal;
                                                            MinTotal = Min + MinTotal;
                                                            count = count + 1;
                                                        }

                                                        long xianMin = MinTotal / count;
                                                        long xianMax = MaxTotal / count;

                                                        solrQuery.set("q", "workplace:沈阳*");
                                                        solrQuery.set("fl", "salary");
                                                        QueryResponse responseshenyang = solrClient.query(solrQuery);
                                                        if (responseshenyang != null) {
                                                            SolrDocumentList resultshenyang = responseshenyang.getResults();
                                                            for (SolrDocument document : resultshenyang) {
                                                                String min = (String) document.getFieldValue("salary");
                                                                if (min == null) {
                                                                    continue;
                                                                }
                                                                String[] strMin = min.split("-");
                                                                String nummin = strMin[0];
                                                                String nummax = strMin[1];
                                                                int Min = Integer.parseInt(nummin);
                                                                int Max = Integer.parseInt(nummax);
                                                                MaxTotal = Max + MaxTotal;
                                                                MinTotal = Min + MinTotal;
                                                                count = count + 1;
                                                            }

                                                            long shenyangMin = MinTotal / count;
                                                            long shenyangMax = MaxTotal / count;

                                                            solrQuery.set("q", "workplace:昆明*");
                                                            solrQuery.set("fl", "salary");
                                                            QueryResponse responsekunming = solrClient.query(solrQuery);
                                                            if (responsekunming != null) {
                                                                SolrDocumentList resultkunming = responsekunming.getResults();
                                                                for (SolrDocument document : resultkunming) {
                                                                    String min = (String) document.getFieldValue("salary");
                                                                    if (min == null) {
                                                                        continue;
                                                                    }
                                                                    String[] strMin = min.split("-");
                                                                    String nummin = strMin[0];
                                                                    String nummax = strMin[1];
                                                                    int Min = Integer.parseInt(nummin);
                                                                    int Max = Integer.parseInt(nummax);
                                                                    MaxTotal = Max + MaxTotal;
                                                                    MinTotal = Min + MinTotal;
                                                                    count = count + 1;
                                                                }

                                                                long kunmingMin = MinTotal / count;
                                                                long kunmingMax = MaxTotal / count;

                                                                solrQuery.set("q", "workplace:东莞*");
                                                                solrQuery.set("fl", "salary");
                                                                QueryResponse responsedongguan = solrClient.query(solrQuery);
                                                                if (responsedongguan != null) {
                                                                    SolrDocumentList resultsdongguan = responsedongguan.getResults();
                                                                    for (SolrDocument document : resultsdongguan) {
                                                                        String min = (String) document.getFieldValue("salary");
                                                                        if (min == null) {
                                                                            continue;
                                                                        }
                                                                        String[] strMin = min.split("-");
                                                                        String nummin = strMin[0];
                                                                        String nummax = strMin[1];
                                                                        int Min = Integer.parseInt(nummin);
                                                                        int Max = Integer.parseInt(nummax);
                                                                        MaxTotal = Max + MaxTotal;
                                                                        MinTotal = Min + MinTotal;
                                                                        count = count + 1;
                                                                    }

                                                                    long dongguanMin = MinTotal / count;
                                                                    long dongguanMax = MaxTotal / count;

                                                                    //新一线城市数据
                                                                    long NewOneNumMin = (dongguanMin + tianjinMin + chongqingMin + nanjingMin + suzhouMin + hangzhouMin + ningboMin + chengduMin + qingdaoMin + zhengzhouMin + wuhanMin + changshaMin + xianMin + shenyangMin + kunmingMin) / 15;
                                                                    long NewOneNumMax = (dongguanMax + tianjinMax + chongqingMax + nanjingMax + suzhouMax + hangzhouMax + ningboMax + chengduMax + qingdaoMax + zhengzhouMax + wuhanMax + changshaMax + xianMax + shenyangMax + kunmingMax) / 15;
                                                                    long NewOneNumAverage = (NewOneNumMin + NewOneNumMax) / 2;
                                                                    Salary salary=new Salary();
                                                                    salary.setMax(NewOneNumMax);
                                                                    salary.setMin(NewOneNumMin);
                                                                    salary.setAvg(NewOneNumAverage);
                                                                    list.add(salary);
                                                                    salaryResult.setSalarylist(list);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return salaryResult;
    }

    @Override
    public SalaryResult Analysissecond() throws IOException, SolrServerException {
        SalaryResult salaryResult=new SalaryResult();
        List<Salary> list=new ArrayList<>();
        int num = distributionMapper.finmum();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRows(num);   //设置查询条数
        solrQuery.setStart(0);//设置查询的开始
        long MaxTotal = 0;//最大值总数
        long MinTotal = 0;//最小值总数
        long count = 0;//计数

        solrQuery.set("q", "workplace:珠海*");
        solrQuery.set("fl", "salary");
        QueryResponse responsezhuhai = solrClient.query(solrQuery);
        if (responsezhuhai != null) {
            SolrDocumentList resultszhuhai = responsezhuhai.getResults();
            for (SolrDocument document : resultszhuhai) {
                String min = (String) document.getFieldValue("salary");
                if (min == null) {
                    continue;
                }
                String[] strMin = min.split("-");
                String nummin = strMin[0];
                String nummax = strMin[1];
                int Min = Integer.parseInt(nummin);
                int Max = Integer.parseInt(nummax);
                MaxTotal = Max + MaxTotal;
                MinTotal = Min + MinTotal;
                count = count + 1;
            }

            long zhuhaiMin = MinTotal / count;
            long zhuhaiMax = MaxTotal / count;

            solrQuery.set("q", "workplace:佛山*");
            solrQuery.set("fl", "salary");
            QueryResponse responsefoshan = solrClient.query(solrQuery);
            if (responsefoshan != null) {
                SolrDocumentList resultsfoshan = responsefoshan.getResults();
                for (SolrDocument document : resultsfoshan) {
                    String min = (String) document.getFieldValue("salary");
                    if (min == null) {
                        continue;
                    }
                    String[] strMin = min.split("-");
                    String nummin = strMin[0];
                    String nummax = strMin[1];
                    int Min = Integer.parseInt(nummin);
                    int Max = Integer.parseInt(nummax);
                    MaxTotal = Max + MaxTotal;
                    MinTotal = Min + MinTotal;
                    count = count + 1;
                }

                long foshanMin = MinTotal / count;
                long foshanMax = MaxTotal / count;

                solrQuery.set("q", "workplace:无锡*");
                solrQuery.set("fl", "salary");
                QueryResponse responsewuxi = solrClient.query(solrQuery);
                if (responsewuxi != null) {
                    SolrDocumentList resultswuxi = responsewuxi.getResults();
                    for (SolrDocument document : resultswuxi) {
                        String min = (String) document.getFieldValue("salary");
                        if (min == null) {
                            continue;
                        }
                        String[] strMin = min.split("-");
                        String nummin = strMin[0];
                        String nummax = strMin[1];
                        int Min = Integer.parseInt(nummin);
                        int Max = Integer.parseInt(nummax);
                        MaxTotal = Max + MaxTotal;
                        MinTotal = Min + MinTotal;
                        count = count + 1;
                    }

                    long wuxiMin = MinTotal / count;
                    long wuxiMax = MaxTotal / count;

                    solrQuery.set("q", "workplace:常州*");
                    solrQuery.set("fl", "salary");
                    QueryResponse responsechangzhou = solrClient.query(solrQuery);
                    if (responsechangzhou != null) {
                        SolrDocumentList resultschangzhou = responsechangzhou.getResults();
                        for (SolrDocument document : resultschangzhou) {
                            String min = (String) document.getFieldValue("salary");
                            if (min == null) {
                                continue;
                            }
                            String[] strMin = min.split("-");
                            String nummin = strMin[0];
                            String nummax = strMin[1];
                            int Min = Integer.parseInt(nummin);
                            int Max = Integer.parseInt(nummax);
                            MaxTotal = Max + MaxTotal;
                            MinTotal = Min + MinTotal;
                            count = count + 1;
                        }

                        long changzhouMin = MinTotal / count;
                        long changzhouMax = MaxTotal / count;

                        solrQuery.set("q", "workplace:福州*");
                        solrQuery.set("fl", "salary");
                        QueryResponse responsefuzhou = solrClient.query(solrQuery);
                        if (responsefuzhou != null) {
                            SolrDocumentList resultfuzhou = responsefuzhou.getResults();
                            for (SolrDocument document : resultfuzhou) {
                                String min = (String) document.getFieldValue("salary");
                                if (min == null) {
                                    continue;
                                }
                                String[] strMin = min.split("-");
                                String nummin = strMin[0];
                                String nummax = strMin[1];
                                int Min = Integer.parseInt(nummin);
                                int Max = Integer.parseInt(nummax);
                                MaxTotal = Max + MaxTotal;
                                MinTotal = Min + MinTotal;
                                count = count + 1;
                            }

                            long fuzhouMin = MinTotal / count;
                            long fuzhouMax = MaxTotal / count;

                            solrQuery.set("q", "workplace:济南*");
                            solrQuery.set("fl", "salary");
                            QueryResponse responsejinan = solrClient.query(solrQuery);
                            if (responsejinan != null) {
                                SolrDocumentList resultjinan = responsejinan.getResults();
                                for (SolrDocument document : resultjinan) {
                                    String min = (String) document.getFieldValue("salary");
                                    if (min == null) {
                                        continue;
                                    }
                                    String[] strMin = min.split("-");
                                    String nummin = strMin[0];
                                    String nummax = strMin[1];
                                    int Min = Integer.parseInt(nummin);
                                    int Max = Integer.parseInt(nummax);
                                    MaxTotal = Max + MaxTotal;
                                    MinTotal = Min + MinTotal;
                                    count = count + 1;
                                }

                                long jinanMin = MinTotal / count;
                                long jinanMax = MaxTotal / count;

                                solrQuery.set("q", "workplace:南昌*");
                                solrQuery.set("fl", "salary");
                                QueryResponse responsenanchang = solrClient.query(solrQuery);
                                if (responsenanchang != null) {
                                    SolrDocumentList resultnanchang = responsenanchang.getResults();
                                    for (SolrDocument document : resultnanchang) {
                                        String min = (String) document.getFieldValue("salary");
                                        if (min == null) {
                                            continue;
                                        }
                                        String[] strMin = min.split("-");
                                        String nummin = strMin[0];
                                        String nummax = strMin[1];
                                        int Min = Integer.parseInt(nummin);
                                        int Max = Integer.parseInt(nummax);
                                        MaxTotal = Max + MaxTotal;
                                        MinTotal = Min + MinTotal;
                                        count = count + 1;
                                    }

                                    long nanchangMin = MinTotal / count;
                                    long nanchangMax = MaxTotal / count;

                                    solrQuery.set("q", "workplace:合肥*");
                                    solrQuery.set("fl", "salary");
                                    QueryResponse responsehefei = solrClient.query(solrQuery);
                                    if (responsehefei != null) {
                                        SolrDocumentList resulthefei = responsehefei.getResults();
                                        for (SolrDocument document : resulthefei) {
                                            String min = (String) document.getFieldValue("salary");
                                            if (min == null) {
                                                continue;
                                            }
                                            String[] strMin = min.split("-");
                                            String nummin = strMin[0];
                                            String nummax = strMin[1];
                                            int Min = Integer.parseInt(nummin);
                                            int Max = Integer.parseInt(nummax);
                                            MaxTotal = Max + MaxTotal;
                                            MinTotal = Min + MinTotal;
                                            count = count + 1;
                                        }

                                        long hefeiMin = MinTotal / count;
                                        long hefeiMax = MaxTotal / count;

                                        solrQuery.set("q", "workplace:哈尔滨*");
                                        solrQuery.set("fl", "salary");
                                        QueryResponse responsehaerbin = solrClient.query(solrQuery);
                                        if (responsehaerbin != null) {
                                            SolrDocumentList resulthaerbin = responsehaerbin.getResults();
                                            for (SolrDocument document : resulthaerbin) {
                                                String min = (String) document.getFieldValue("salary");
                                                if (min == null) {
                                                    continue;
                                                }
                                                String[] strMin = min.split("-");
                                                String nummin = strMin[0];
                                                String nummax = strMin[1];
                                                int Min = Integer.parseInt(nummin);
                                                int Max = Integer.parseInt(nummax);
                                                MaxTotal = Max + MaxTotal;
                                                MinTotal = Min + MinTotal;
                                                count = count + 1;
                                            }
                                        }
                                        long haerbinMin = MinTotal / count;
                                        long haerbinMax = MaxTotal / count;

                                        solrQuery.set("q", "workplace:大连*");
                                        solrQuery.set("fl", "salary");
                                        QueryResponse responsedalian = solrClient.query(solrQuery);
                                        if (responsedalian != null) {
                                            SolrDocumentList resultdalian = responsedalian.getResults();
                                            for (SolrDocument document : resultdalian) {
                                                String min = (String) document.getFieldValue("salary");
                                                if (min == null) {
                                                    continue;
                                                }
                                                String[] strMin = min.split("-");
                                                String nummin = strMin[0];
                                                String nummax = strMin[1];
                                                int Min = Integer.parseInt(nummin);
                                                int Max = Integer.parseInt(nummax);
                                                MaxTotal = Max + MaxTotal;
                                                MinTotal = Min + MinTotal;
                                                count = count + 1;
                                            }

                                            long dalianMin = MinTotal / count;
                                            long dalianMax = MaxTotal / count;

                                            solrQuery.set("q", "workplace:长春*");
                                            solrQuery.set("fl", "salary");
                                            QueryResponse responsecahngchun = solrClient.query(solrQuery);
                                            if (responsecahngchun != null) {
                                                SolrDocumentList resultchangchun = responsecahngchun.getResults();
                                                for (SolrDocument document : resultchangchun) {
                                                    String min = (String) document.getFieldValue("salary");
                                                    if (min == null) {
                                                        continue;
                                                    }
                                                    String[] strMin = min.split("-");
                                                    String nummin = strMin[0];
                                                    String nummax = strMin[1];
                                                    int Min = Integer.parseInt(nummin);
                                                    int Max = Integer.parseInt(nummax);
                                                    MaxTotal = Max + MaxTotal;
                                                    MinTotal = Min + MinTotal;
                                                    count = count + 1;
                                                }

                                                long changchunMin = MinTotal / count;
                                                long changchunMax = MaxTotal / count;

                                                //二线城市数据
                                                long SecondNumMin = (zhuhaiMin + foshanMin + wuxiMin + changzhouMin + fuzhouMin + jinanMin + nanchangMin + hefeiMin + haerbinMin + dalianMin + changchunMin) / 11;
                                                long SecondNumMax = (zhuhaiMax + foshanMax + wuxiMax + changzhouMax + fuzhouMax + jinanMax + nanchangMax + hefeiMax + haerbinMax + dalianMax + changchunMax) / 11;
                                                long SecondNumAverage = (SecondNumMin + SecondNumMax) / 2;
                                                Salary salary=new Salary();
                                                salary.setMax(SecondNumMax);
                                                salary.setMin(SecondNumMin);
                                                salary.setAvg(SecondNumAverage);
                                                list.add(salary);
                                                salaryResult.setSalarylist(list);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return salaryResult;
    }
}

