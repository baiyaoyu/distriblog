package com.hiddencat.picsmgt.config;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/9
 * @Description:
 */
@Configuration
public class RestemplateConfig {

    @Bean
    public RestTemplate genRestemplate(){
        HttpMessageConverter messageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        RestTemplateBuilder builder = new RestTemplateBuilder().
                setConnectTimeout(Duration.ofSeconds(10)).
                setReadTimeout(Duration.ofSeconds(10)).
                additionalMessageConverters(messageConverter);
        RestTemplate restTemplate = builder.build();
        restTemplate.setRequestFactory(createFactory());
        return restTemplate;
    }

    /**
     * 支持https 及  http
     * @param httpClientBuilder HttpClientBuilder
     * @param maxTotal 最大连接数
     * @param perRouter 路由
     * @return
     */
    private HttpClientBuilder getHttpClientBuilder(HttpClientBuilder httpClientBuilder, int maxTotal, int perRouter){

        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyManagementException e) {
        } catch (KeyStoreException e) {
        }

        httpClientBuilder.setSSLContext(sslContext);

        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager( socketFactoryRegistry);
        connMgr.setMaxTotal(maxTotal);
        connMgr.setDefaultMaxPerRoute(perRouter);

        httpClientBuilder.setConnectionManager(connMgr);
        return httpClientBuilder;
    }

    /**
     *
     * 创建HTTP客户端工厂<br/>
     *
     * @return  org.springframework.http.client.ClientHttpRequestFactory
     * @author  zfw2660
     * @date    2018/9/18 9:22
     * @other
     * @exception
     *
     */
    private ClientHttpRequestFactory createFactory() {
        HttpClient httpClient =getHttpClientBuilder(HttpClientBuilder.create(),100,100).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(10000);
        return factory;
    }

}
