package fgh.weixin.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送短信工具类
 * 
 * @author fgh
 * @Since 2016-5-9 下午3:43:49
 */
public class SendMsgUtil {

	private static final Logger logger = LoggerFactory.getLogger(SendMsgUtil.class);

	private static final String SEND_URL = "sendUrl";
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";
	private static final String CHARSET = "charset";

	private static final Properties prop = new Properties();

	static {
		InputStream fis = SendMsgUtil.class.getClassLoader().getResourceAsStream("sendMsg.properties");
		try {
			prop.load(fis);
		} catch (IOException e) {
			logger.error("读取sendMsg.properties文件失败", e);
		}
	}

	/**
	 * 发送短信
	 * 
	 * @param mobileNumber
	 *            手机号
	 * @param content
	 *            短信内容
	 * @return
	 * @throws SocketTimeoutException
	 * @throws Exception
	 */
	public static String httpPostSend(String mobileNumber, String content) throws SocketTimeoutException, Exception {

		// 请求参数
		String reqparam = "CorpID=" + getUserName() + "&Pwd=" + getPassword() + "&Content=" + content + "&Mobile="
				+ mobileNumber;

		String sendUrl = getSendUrl();
		logger.info("发送短信reqparam[" + reqparam + "],短信服务器地址[" + sendUrl + "]");

		StringBuffer sbf = new StringBuffer(64);
		BufferedWriter writer = null;
		BufferedReader reader = null;
		HttpURLConnection uc = null;
		String charset = getCharset();
		try {
			URL url = new URL(sendUrl);
			uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(30000);
			uc.setReadTimeout(30000);
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			uc.setDoInput(true);

			writer = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), charset)); // 向服务器传送数据
			writer.write(reqparam);
			writer.flush();
			writer.close();
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), charset)); // 读取服务器响应信息
			String line;

			while ((line = reader.readLine()) != null) {
				sbf.append(line);
			}
			reader.close();
			uc.disconnect();
		} catch (SocketTimeoutException e) {
			throw new SocketTimeoutException();
		} catch (IOException e) {
			logger.error("短信发送失败,reqparam[" + reqparam + "],短信服务器地址[" + sendUrl + "],返回内容：[" + sbf + "]", e);
			throw new IOException();
		} catch (Exception e) {
			logger.error("短信发送失败,reqparam[" + reqparam + "],短信服务器地址[" + sendUrl + "],返回内容：[" + sbf + "]", e);
		} finally {
			closeIO(writer, reader);
		}
		logger.info("短信发送成功,reqparam[" + reqparam + "],短信服务器地址[" + sendUrl + "],返回内容：[" + sbf + "]");
		return sbf.toString().trim();
	}

	/**
	 * 关闭连接
	 * 
	 * @param writer
	 * @param reader
	 */
	private static void closeIO(BufferedWriter writer, BufferedReader reader) {
		if (writer != null) {
			try {
				writer.close();
				writer = null;
			} catch (Exception e) {

			}
		}
		if (reader != null) {
			try {
				reader.close();
				reader = null;
			} catch (Exception e) {

			}
		}
	}

	// 获取发送地址
	public static String getSendUrl() {
		return prop.getProperty(SEND_URL);
	}

	// 获取用户名
	public static String getUserName() {
		return prop.getProperty(USER_NAME);
	}

	// 获取密码
	public static String getPassword() {
		return prop.getProperty(PASSWORD);
	}

	// 获取编码
	public static String getCharset() {
		return prop.getProperty(CHARSET);
	}
	
	public static Properties getProp(){
		return prop;
	}
}
