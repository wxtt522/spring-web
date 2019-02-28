package study.tools;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * rest请求三方工具 Created by suny on 2017/4/20.
 */
public class RequestUtils {
	// 已开通测试和正式账号，测试账号配置了100条，正式账号慧眼、探真2w条，黑名单不限数量
	//
	// 测试账号 : test_dfjr
	// 密码 : dfjr-ICKtest-0428
	//
	// 绑定服务 : 
	// 达富金融（慧眼分18版），对应service参数：dfjr_huiyan18
	// 达富金融（黑名单） ，对应service参数：dfjr_blacklist
	// 探真，对应service参数：multi_loan
	// :
	// 正式账号你要的时候再跟我说
	// private static Log logger = LogFactory.getLog(RequestUtils.class);

	// 登录
	// public static void main(String[] args) throws Throwable {
	// String url = "https://portal.icekredit.com/api/login.do";
	// Map<String, String> req = new HashMap<>();
	// // 传入的参数
	// String merchant_name = "test_dfjr";
	// String merchant_pwd = "dfjr-ICKtest-0428";
	// merchant_pwd = MD5Utils.getMD5Sign(merchant_pwd);
	// req.put("merchant_name", merchant_name);
	// req.put("merchant_pwd", merchant_pwd);
	//
	// // obj to json
	// String reqStr = JsonUtils.getJson(req);
	// System.out.println("req:" + reqStr);
	// String result = post(url, req);
	// System.out.println("resp:" + result);
	//
	// Map<String, Object> requestMap = ThirdCommonUtils.jsonToMap(result);
	//
	// Set<Map.Entry<String, Object>> set = requestMap.entrySet();
	//
	// for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();)
	// {
	//
	// Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	// System.out.println("**************");
	// System.out.println(entry.getKey() + "=" + entry.getValue());
	// }
	// }

	// 黑名单和评分
	// public static void main(String[] args) throws Throwable {
	// String url =
	// "https://hawkeye.icekredit.com/api/credit?token_id=1FFE42088BA105363375B25A47868906";
	// Map<String, Object> req = new HashMap<>();
	// Map<String, Object> baseInfo = new HashMap<>();
	// baseInfo.put("name", "吴丹山");
	// baseInfo.put("id", "522323197102018487");
	// baseInfo.put("cell", "13700120089");
	// baseInfo.put("account_num_1", "");
	// baseInfo.put("service", "dfjr_blacklist");
	//// baseInfo.put("service", "dfjr_huiyan18");
	// req.put("baseInfo", baseInfo);
	// // 传入的参数
	//
	// // obj to json
	// String reqStr = JsonUtils.getJson(req);
	// System.out.println("req:" + reqStr);
	// String result = post(url, reqStr);
	// System.out.println("resp:" + result);
	//
	// Map<String, Object> requestMap = ThirdCommonUtils.jsonToMap(result);
	//
	// Set<Map.Entry<String, Object>> set = requestMap.entrySet();
	//
	// for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();)
	// {
	//
	// Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	// System.out.println("**************");
	// System.out.println(entry.getKey() + "=" + entry.getValue());
	// }
	// }

	// 探真

	/**
	 * https post方法
	 *
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String json) throws Exception {
		if (url != null) {
			HttpClient httpClient;
			if (url.startsWith("https")) {
				httpClient = getHttpsClient();
			} else {
				httpClient = getHttpClient();
			}

			StringEntity entity = new StringEntity(json, "UTF-8");
			return post(httpClient, url, entity);
		} else {
			return "url is null";
		}
	}

	/**
	 * https post方法
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params) throws Exception {
		if (url == null) {
			return "url is null";
		} else {
			HttpClient httpClient;
			if (url.startsWith("https")) {
				httpClient = getHttpsClient();
			} else {
				httpClient = getHttpClient();
			}

			ArrayList paras = new ArrayList();
			Iterator entity = params.keySet().iterator();

			while (entity.hasNext()) {
				String key = (String) entity.next();
				paras.add(new BasicNameValuePair(key, (String) params.get(key)));
			}
			UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(paras, "UTF-8");
			return post(httpClient, url, entity1);
		}
	}

	/**
	 * https post方法
	 *
	 * @param httpClient
	 * @param url
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private static String post(HttpClient httpClient, String url, HttpEntity entity) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();

		String response = EntityUtils.toString(httpEntity, "utf-8");
		EntityUtils.consume(httpEntity);
		return response;
	}

	private static HttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static HttpClient getHttpsClient() throws Exception {
		SSLContext context = (new SSLContextBuilder()).loadTrustMaterial((KeyStore) null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return true;
			}
		}).build();
		return HttpClients.custom().setSslcontext(context).build();
	}
}