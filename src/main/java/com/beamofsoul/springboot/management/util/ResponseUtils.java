package com.beamofsoul.springboot.management.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ResponseUtils
 * @Description HttpServletResponse对应的工具类
 * @author MingshuJian
 * @Date 2017年9月28日 下午4:18:46
 * @version 1.0.0
 */
public class ResponseUtils {
	
	public static PrintWriter getHtmlWriter(final HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.flush();
		return out;
	}

	public static void writeJavascript(PrintWriter out, String javascript) {
		out.println("<script type=\"text/javascript\">");
		out.println(javascript);
        out.println("</script>");
	}
}
