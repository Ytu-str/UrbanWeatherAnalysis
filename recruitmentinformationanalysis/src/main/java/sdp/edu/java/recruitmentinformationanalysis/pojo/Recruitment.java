package sdp.edu.java.recruitmentinformationanalysis.pojo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "Recruitment")
public class Recruitment {
    @Field("position")
    private String position;
    @Field("company")
    private String company;
    @Field("worksplace")
    private String workplace;
    @Field("salary")
    private String salary;
    @Field("pubdate")
    private String pubdate;
    @Field("id")
    private Integer id;
    public Recruitment(){}
    public Recruitment(String position, String company, String workplace, String salary, String pubdate, Integer id) {
        this.position = position;
        this.company = company;
        this.workplace = workplace;
        this.salary = salary;
        this.pubdate = pubdate;
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String worksplace) {
        this.workplace = worksplace;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
