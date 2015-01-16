package codeQuantiyMeasurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodeQuantityMeasurement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CodeQuantityMeasurement cqm = new CodeQuantityMeasurement();
		String path = "C:/Users/clli/git/lc";
		//String path = "C:/test";
		System.out.println("Caculating...");
		int totalLineCount = cqm.run(path);
		System.out.println("Total line of code is: "+ totalLineCount);
		
	}
	
	public int run(String path) {
		ArrayList<String> filesToCount = new ArrayList<String>();
		traverseDirectory(filesToCount, path);
		System.out.println("Under " + path + ":");
		System.out.println("Total files is: " + filesToCount.size());
		int totalCount = 0;
		for (String filepath : filesToCount) {
			totalCount += countLine(filepath);
		}
		return totalCount;
	}
	
	private void traverseDirectory(ArrayList<String> result, String path) {
		File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) {
        	return;
        }
		for (File f : list) {
			if (f.isDirectory()) {
				traverseDirectory(result, f.getAbsolutePath());
				//System.out.println("Dir:" + f.getAbsoluteFile());
			} else {
				String fileName = f.getAbsolutePath();
				if (fileName.length() - fileName.lastIndexOf(".java") == 5) {
					//System.out.println(fileName);
					result.add(fileName);
				}
			}
		}
	}
	
	private int countLine(String fileName) {
		File f = new File(fileName);
		int lineNumber = 1;
		boolean ready4public = false;
		int start = lineNumber;
		int end = lineNumber;
		int afterEnd = -1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains("main(String[] args)")) {
					ready4public = true;
				} else if (line.contains("public ") && ready4public) {
					start = lineNumber;
				} else if (line.contains("}")) {
					if (afterEnd != -1) {
						end = afterEnd;
					}
					afterEnd = lineNumber;
				}
				//System.out.println(line);
				lineNumber++;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		System.out.println("start: " + start);
		System.out.println("end: " + end);
		System.out.println("lineNumber: " + lineNumber);*/
		return end - start + 1;
	}
	
	

}
