
public class Author {
	String status;
	String copyright;
	int num_results;
	result[] results;

	
	
	public result[] getResults() {
		return results;
	}
	public void setResults(result[] results) {
		results = results;
	}
	
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
	public int getNum_results() {
		return num_results;
	}
	public void setNum_results(int num_results) {
		this.num_results = num_results;
	}
	
	
}
