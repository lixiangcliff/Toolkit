package urlMaking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class UrlMaking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UrlMaking um = new UrlMaking();
		//String path = "C:/test/questionName.txt";
		String path = "C:/test/qn2.txt";
		ArrayList <Integer> changedLine = new ArrayList<Integer>();
		um.makeUrl(changedLine, path);
/*		for (int num : changedLine) {
			System.out.print(num + ", ");
		}*/

	}
	
	private void makeUrl (ArrayList <Integer> changedLine, String path) {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			int lineNumber = 2;
			while ((sCurrentLine = br.readLine()) != null) {
				//processLineLint(sCurrentLine);
				processLineGit(sCurrentLine);
				//System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//question link for leetcode
	private String processLineLint(String str) {
		String strOrig = new String(str.toLowerCase().toCharArray());
		str = str.toLowerCase();
		str = str.replace("'", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(",", "");
		
		str = str.replace(' ', '-');
		String url = "http://lintcode.com/en/problem/" + str + "/";
		System.out.println(url);
		return url;
	}
	
	//question link for leetcode
	private String processLineLeet(String str) {
		String strOrig = new String(str.toLowerCase().toCharArray());
		str = str.toLowerCase();
		str = str.replace("'", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(",", "");
		str = str.replace(' ', '-');
		String url = "https://oj.leetcode.com/problems/" + str + "/";
		System.out.println(url);
		return url;
	}
	
	//solution link for github
	private String processLineGit(String str) {
		str = str.replace("'", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(",", "");
		str = str.replace(" ", "_");		
		//String url = "https://github.com/lixiangcliff/leetcode/tree/master/lc/" + str + "/Question.java"; // leetcode
		String url = "https://github.com/lixiangcliff/leetcode/tree/master/lc/" + str + "/Solution.java"; // lintcode
		System.out.println(url);
		return url;
	}

}
