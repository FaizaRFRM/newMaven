import java.sql.Date;

public class docs {
//	status,copyright,response,docs(web_url,source,pub_date,section_name,subsection_name,byline{original,organization},_id,uri),
	private String web_url;
	private String source;
	private String pub_date;
	private String section_name;
	private String subsection_name;
	
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	
	
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getSubsection_name() {
		return subsection_name;
	}
	public void setSubsection_name(String subsection_name) {
		this.subsection_name = subsection_name;
	}
	
}
