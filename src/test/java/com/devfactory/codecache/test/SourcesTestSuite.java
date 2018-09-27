package com.devfactory.codecache.test;

import com.devfactory.codecache.util.ExecutionHelper;
import com.devfactory.codecache.util.PerformanceTestHelper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Slf4j
class SourcesTestSuite {

    private final PerformanceTestHelper testHelper = new PerformanceTestHelper();
    private final ExecutionHelper executor = new ExecutionHelper();

    @ParameterizedTest(name = "repository_url=''{1}'', parallelism=''{2}'', times=''{3}''")
    @CsvSource({
            "1000, https://github.com/spring-projects/spring-retry.git?branch=master&rev=HEAD, 1, 1"
    })
    void sources(long repositoryId, String scmUrl, int parallelism, int times) {
        log.info("executing test suite for {}", repositoryId);

        List<Long> results = new ArrayList<>();
        executor.runParallel(parallelism, times, () -> results.add(testHelper.getSourcesExecutionTime(scmUrl)));
        testHelper.logOutput("SOURCES", results, repositoryId, parallelism);
    }


    @ParameterizedTest(name = "repository_url=''{1}'', parallelism=''{2}'', times=''{3}''")
    @CsvSource({
            "1000, https://github.com/spring-projects/spring-retry.git?branch=master&rev=HEAD&path=pom.xml, 1, 1"
    })
    void annotations(long repositoryId, String scmUrl, int parallelism, int times) {
        log.info("executing test suite for {}", repositoryId);

        List<Long> results = new ArrayList<>();
        executor.runParallel(parallelism, times, () -> results.add(testHelper.getAnnotations(scmUrl)));
        testHelper.logOutput("ANNOTATIONS", results, repositoryId, parallelism);
    }

}

