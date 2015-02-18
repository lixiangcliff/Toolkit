package randomNaming;

import java.io.File;
import java.math.BigInteger;
import java.util.Random;
import java.security.SecureRandom;

public class randomNaming {

	/**
	 * 批量重命名文件名为随机字母数字组合
	 */
	public static void main(String[] args) {
		String path = "E:/";
		randomNaming rn = new randomNaming();
		rn.run(path);
	}
	
	public void run(String path) {
		System.out.println("Under " + path + ":");
		traverseDirectory(path);
	}
	
	private void traverseDirectory(String path) {
		File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) {
        	return;
        }
		for (File f : list) {
			if (f.isDirectory()) {
				traverseDirectory(f.getAbsolutePath());
				//System.out.println("Dir:" + f.getAbsoluteFile());
			} else {
				String fileName = f.getAbsolutePath();
				String ext = fileName.substring(fileName.lastIndexOf("."));
				if (ext.toLowerCase().equals(".jpeg") || ext.toLowerCase().equals(".jpg")) {
					renameFile(fileName);
				}
			}
		}
	}
	
	//重命名文件
	private void renameFile(String oldName) {
		String ext = oldName.substring(oldName.lastIndexOf("."));
		String header = oldName.substring(0, oldName.lastIndexOf("\\"));
		String newName = "";
		do { // 防止随机名和原来的名字一样
			String randomName = generateRandomName();
			newName = header + "\\" + randomName + ext;
		} while (newName == oldName);
		File oldfile = new File(oldName);
		File newfile = new File(newName);
		if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
			System.out.println(newName + "已经存在！");
		else {
			System.out.println("orig name is: " + oldName + "; " + "new name is: " + newName);
			oldfile.renameTo(newfile);
		}
	}
	
	private String generateRandomName() {
		RandomString rs = new RandomString(16);
		String randomName = rs.nextString();
/*		SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
		String randomName = sig.nextSessionId();*/
		return randomName;
	}	
}

//http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
class RandomString {
	private static final char[] symbols;
	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();
	}

	private final Random random = new Random();

	private final char[] buf;

	public RandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		buf = new char[length];
	}

	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}
}

class SessionIdentifierGenerator {
	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
}