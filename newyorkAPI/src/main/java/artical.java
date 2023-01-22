import java.util.Scanner;

public class artical {

//	status,copyright,response,docs(web_url,source,pub_date,section_name,subsection_name,byline{original,person[]},_id,uri),
     private  String status;
     private  String copyright;
     private  response Response;
     
     
     public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public response getResponse() {
		return Response;
	}
	public void setResponse(response response) {
		Response = response;
	}
	
    
	
}
