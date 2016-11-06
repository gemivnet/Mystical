package terms;

public class Term {

	String[] def, sus;
	int ans;
	boolean learned = false;
	String q;
	
	public Term(String[] definition, String question, String[] suspects, int answer) {
		def = definition;
		sus = suspects;
		ans = answer;
		q = question;
	}
	
	public boolean hasLearned() {
		return learned;
	}
	
	public void setLearned() {
		learned = true;
	}
	
	public String[] getDef() {
		return def;
	}
	
	public String getSus(int i) {
		return sus[i];
	}
	
	public int getAns() {
		return ans;
	}
	
	public String getQuestion() {
		return q;
	}
	
	public void setCompleted() {
		ans = 0;
	}
	
}
