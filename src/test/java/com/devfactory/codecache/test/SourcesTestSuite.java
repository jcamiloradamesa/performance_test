package com.devfactory.codecache.test;

import com.devfactory.codecache.util.ExecutionHelper;
import com.devfactory.codecache.util.PerformanceTestHelper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
class SourcesTestSuite {

    private static final int TIMES = 10;

    private final PerformanceTestHelper testHelper = new PerformanceTestHelper();
    private final ExecutionHelper executor = new ExecutionHelper();

    @BeforeAll
    @SneakyThrows
    void setup() {
        Path output = Paths.get("output");

        FileUtils.deleteDirectory(output.toFile());
        Files.createDirectory(output);
    }

    @ParameterizedTest(name = "repository_url=''{1}'', parallelism=''{2}''")
    @CsvFileSource(resources = "/repositories.csv")
    void sources(long repositoryId, String scmUrl, int parallelism) {
        log.info("executing test suite for {}", repositoryId);

        List<Long> results = new ArrayList<>();
        executor.runParallel(parallelism, TIMES, () -> results.add(testHelper.getSourcesExecutionTime(scmUrl)));
        testHelper.logOutput("SOURCES", results, repositoryId, parallelism);
    }


    @ParameterizedTest(name = "repository_url=''{1}'', parallelism=''{2}''")
    @CsvFileSource(resources = "/repositories.csv")
    void annotations(long repositoryId, String scmUrl, int parallelism) {
        log.info("executing test suite for {}", repositoryId);

        List<Long> results = new ArrayList<>();
        executor.runParallel(parallelism, TIMES, () -> results.add(testHelper.getAnnotations(scmUrl)));
        testHelper.logOutput("ANNOTATIONS", results, repositoryId, parallelism);
    }

}

