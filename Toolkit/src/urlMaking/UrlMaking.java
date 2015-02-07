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
				//processLine(changedLine, sCurrentLine, lineNumber);
				processLine2(sCurrentLine);
				lineNumber++;
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
	private String processLine(ArrayList<Integer> changedLine, String str, int lineNumber) {
		String strOrig = new String(str.toLowerCase().toCharArray());
		str = str.toLowerCase();
		str = str.replace("'", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(",", "");
		if (!str.equals(strOrig)) {
			changedLine.add(lineNumber);
		}
		str = str.replace(' ', '-');
/*		if (lineNumber == 95) {
			System.out.println(strOrig);
			System.out.println(str);
		}*/
		String url = "https://oj.leetcode.com/problems/" + str + "/";
		System.out.println(url);
		return url;
	}
	
	//solution link for github
	private String processLine2(String str) {
		str = str.replace("'", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(",", "");
		str = str.replace(" ", "_");
/*		if (lineNumber == 95) {
			System.out.println(strOrig);
			System.out.println(str);
		}*/
		
		String url = "https://github.com/lixiangcliff/leetcode/tree/master/lc/" + str + "/Question.java";
		System.out.println(url);
		return url;
	}

}
