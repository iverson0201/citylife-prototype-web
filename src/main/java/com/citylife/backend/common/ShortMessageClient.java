package com.citylife.backend.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月23日 下午1:46:56
 */
public class ShortMessageClient {

	private Logger logger = LoggerFactory.getLogger(ShortMessageClient.class);
	private String serviceURL = "http://sdk2.zucp.net:8060/webservice.asmx";

	private String sn = "";// ???
	private String password = "";
	private String pwd = "";// ??

	public ShortMessageClient(String sn, String password) {
		super();
		this.sn = sn;
		this.setPassword(password);
		this.pwd = this.getMd5(sn + password);
	}

	private String getMd5(String sourceStr) {
		// TODO Auto-generated method stub
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			// resultStr = new String(md5.digest());
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发送短信
	 * @param mobile
	 * @param content
	 * @param ext
	 * @param stime
	 * @param rrid
	 * @return
	 */
	public String mdSmsSend_u(String mobile, String content, String ext,
			String stime, String rrid) {
		String result = "";
		String soapAction = "http://tempuri.org/mdSmsSend_u";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mdSmsSend_u xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + content + "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "<stime>" + stime + "</stime>";
		xml += "<rrid>" + rrid + "</rrid>";
		xml += "</mdSmsSend_u>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		// System.out.println(sn+"  "+pwd+"  "+mobile+"  "+content);
		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes("GBK"));
			// ???????utf-8,?????bout.write(xml.getBytes("GBK"));

			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length",
					String.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(
					httpconn.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern
						.compile("<mdSmsSend_uResult>(.*)</mdSmsSend_uResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			logger.info("result: " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
