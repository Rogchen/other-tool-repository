package com.ylzinfo.ms.utils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Description
 * @Author Rogchen rogchen128@gmail.com
 * @Created 2018/2/15 15:49
 **/
public class HttpClientUtil {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HttpClientUtil.class);


    private static HttpRequestFactory requestFactory = null;

    static {
//		NetHttpTransport transport = new NetHttpTransport.Builder().setProxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress(80))).build();
        NetHttpTransport transport = new NetHttpTransport();
        requestFactory = transport.createRequestFactory();
    }

    /**
     * POST请求,JSON参数,不带Header
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static String doPost(String url, String jsonParam) {
        return doPost(url, jsonParam, null);
    }

    /**
     * POST请求,JSON参数,带Header
     *
     * @param url
     * @param jsonParam
     * @param headers
     * @return
     */
    public static String doPost(String url, String jsonParam, Map<String, String> headers) {
        String resData = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(processUrl(url));

            // 参数header设置
            if (headers != null) {
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpPost.addHeader(key, headers.get(key));
                }
            }

            // 参数请求设置
            if (!StringUtils.isBlank(jsonParam)) {
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                // entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                // "application/json"));
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            // 发起请求
            response = httpclient.execute(httpPost);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resData = EntityUtils.toString(response.getEntity());
            } else {
                log.error("http post error status [ " + status + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("http post Exception [ " + ex + "]");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("http post IOException [ " + e + "]");
            }
        }
        return resData;
    }

    /**
     * GET请求(不处理url特殊字符),不带Header参数
     *
     * @param url 请求地址
     * @return
     */
    public static String doGetNoProcess(String url) {
        return doGetNoProcess(url, null);
    }

    /**
     * GET请求(不处理url特殊字符),带Header参数
     *
     * @param url
     * @param headers
     * @return
     */
    public static String doGetNoProcess(String url, Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resData = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);

            // 设置header参数
            if (headers != null) {
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpGet.addHeader(key, headers.get(key));
                }
            }
            response = httpclient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resData = EntityUtils.toString(response.getEntity());
            } else {
                log.error("http doGet error status [ " + status + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("http doGet Exception [ " + ex + "]");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("http doGet IOException [ " + e + "]");
            }
        }
        return resData;
    }

    /**
     * GET请求,不带Header参数
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * GET请求,带Header参数
     *
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(String url, Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resData = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(processUrl(url));

            // 设置header参数
            if (headers != null) {
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpGet.addHeader(key, headers.get(key));
                }
            }

            response = httpclient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resData = EntityUtils.toString(response.getEntity());
            } else {
                log.error("http doGet error status [ " + status + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("http doGet Exception [ " + ex + "]");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("http doGet IOException [ " + e + "]");
            }
        }
        return resData;
    }

    /**
     * PUT请求,JSON参数
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static String doPut(String url, String jsonParam) {
        CloseableHttpClient httpclient = null;
        String resData = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(processUrl(url));
            if (!StringUtils.isBlank(jsonParam)) {
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentType("application/json");
                httpPut.setEntity(entity);
            }
            response = httpclient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resData = EntityUtils.toString(response.getEntity());
            } else {
                log.error("http put error status [ " + status + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("http put Exception [ " + ex + "]");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("http put IOException [ " + e + "]");
            }
        }
        return resData;
    }

    /**
     * PUT请求,JSON参数
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static String doPut(String url, String jsonParam, Map<String, String> headers) {
        CloseableHttpClient httpclient = null;
        String resData = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(processUrl(url));

            // 参数header设置
            if (headers != null) {
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpPut.addHeader(key, headers.get(key));
                }
            }

            if (!StringUtils.isBlank(jsonParam)) {
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentType("application/json");
                httpPut.setEntity(entity);
            }
            response = httpclient.execute(httpPut);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resData = EntityUtils.toString(response.getEntity());
            } else {
                log.error("http put error status [ " + status + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("http put Exception [ " + ex + "]");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("http put IOException [ " + e + "]");
            }
        }
        return resData;
    }

    /**
     * 异步GET请求,不带Header参数
     *
     * @param url
     * @return
     */
    public static void doAsyncGet(String url) {
        doAsyncGet(url, null);
    }

    /**
     * 异步GET请求,带Header参数
     *
     * @param url     请求地址
     * @param headers header参数
     */
    public static void doAsyncGet(String url, Map<String, String> headers) {
        try {
            GenericUrl gurl = new GenericUrl(processUrl(url));
            HttpRequest request = requestFactory.buildGetRequest(gurl);
            if (headers != null) {
                // 设置请求头参数
                HttpHeaders httpHeaders = request.getHeaders();
                headers.putAll(headers);
                request.setHeaders(httpHeaders);
            }
            request.executeAsync();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String inputStream2String(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = input.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    /**
     * 异步POST请求,JSON参数
     *
     * @param url
     * @param jsonParam
     */
    public static void doAsyncPost(String url, String jsonParam) {
        try {
            HttpContent content = new ByteArrayContent("application/json",
                    jsonParam.getBytes(Charset.forName("UTF-8")));
            GenericUrl gurl = new GenericUrl(processUrl(url));
            HttpRequest request = requestFactory.buildPostRequest(gurl, content);
            request.executeAsync();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 异步GET请求,失败重试三次
     *
     * @param url         请求URL
     * @param count       计数器
     * @param repeatCount 重试次数
     * @return
     */
    public static String doRetryAsyncGet(String url, AtomicInteger count, Integer repeatCount) {
        String result = null;
        // 执行一次,累加
        count.incrementAndGet();
        try {
            GenericUrl gurl = new GenericUrl(processUrl(url));
            HttpRequest request = requestFactory.buildGetRequest(gurl);
            result = inputStream2String(request.executeAsync().get().getContent());
        } catch (Exception e) {
            if (count.get() < repeatCount) {
                // 重试三次
                doRetryAsyncGet(url, count, repeatCount);
            } else {
                // 超过三次抛出异常
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 请求URL特殊字符处理
     *
     * @param urlStr
     * @return
     */
    public static String processUrl(String urlStr) {
        URL url = null;
        URI uri = null;
        try {
            url = new URL(urlStr);
            uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }

    /**
     * POST请求,Form表单提交,不带Header
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, null);
    }

    /**
     * POST请求,Form表单提交,带Header
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headers) {
        CloseableHttpClient httpclient = null;
        String resData = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(processUrl(url));

            // NameValuePair数组对象用于传入参数
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=" + "UTF-8");// 在头文件中设置转码

            // 参数header设置
            if (headers != null) {
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpPost.addHeader(key, headers.get(key));
                }
            }

            // 参数请求设置
            if (params != null) {
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(putParams(params), "UTF-8");
                httpPost.setEntity(uefEntity);
            }

            // 发起请求
            response = httpclient.execute(httpPost);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                resData = EntityUtils.toString(response.getEntity());
            } else {
                log.error("http post error status [ " + status + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("http post Exception [ " + ex + "]");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("http post IOException [ " + e + "]");
            }
        }
        return resData;
    }

    /**
     * 组织httpClient参数
     *
     * @param paramMap
     * @return
     */
    public static List<NameValuePair> putParams(Map<String, String> paramMap) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            Entry<String, String> entry = iterator.next();

            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return params;
    }

}
