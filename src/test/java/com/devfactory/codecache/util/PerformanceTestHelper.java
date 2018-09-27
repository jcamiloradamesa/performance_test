package com.devfactory.codecache.util;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonList;

import com.google.common.base.Stopwatch;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class PerformanceTestHelper {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String codeCacheUrl;

    public PerformanceTestHelper() {
        codeCacheUrl = PropertyLoader.getProperties().getProperty("codecache_url");
    }

    @SneakyThrows
    public long getSourcesExecutionTime(String scmUrl) {
        return requestData(codeCacheUrl + "sources.zip?dfScmUrl=" + encode(scmUrl, UTF_8.name()));
    }

    @SneakyThrows
    public long getAnnotations(String scmUrl) {
        return requestData(codeCacheUrl + "annotations?dfScmUrl=" + encode(scmUrl, UTF_8.name()));
    }

    private long requestData(String url) {
        Stopwatch timer = Stopwatch.createStarted();
        try {
            restTemplate.exchange(URI.create(url), HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), byte[].class);
        } catch (Exception e) {
            log.info("request could not be completed, {}", e.getMessage());
            return -1;
        }

        timer.stop();
        return timer.elapsed(TimeUnit.MILLISECONDS);
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_OCTET_STREAM));
        return headers;
    }

    @SneakyThrows
    public void logOutput(String prefix, List<Long> data, long repositoryId, int parallelism) {
        String fileName = String.format("%s_repo_%s_%d_times.txt", prefix, repositoryId, parallelism);
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        data.forEach(writer::println);
        writer.close();
    }
}
