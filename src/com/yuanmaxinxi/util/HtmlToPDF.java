package com.yuanmaxinxi.util;

import java.io.File;

public class HtmlToPDF {
	private static String toPDFTool = "/usr/bin/wkhtmltopdf";

	/**
	 * html转pdf
	 *
	 * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
	 * @param destPath pdf保存路径
	 * @return 转换成功返回true
	 */
	public static boolean convert(String srcPath, String destPath) {
		File file = new File(destPath);
		File parent = file.getParentFile();
		// 如果pdf保存路径不存在，则创建路径
		if (!parent.exists()) {
			parent.mkdirs();
		}
		try {
			Process proc = Runtime.getRuntime().exec(toPDFTool + " " + srcPath + " " + destPath);
			proc.waitFor();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		
	}

}
