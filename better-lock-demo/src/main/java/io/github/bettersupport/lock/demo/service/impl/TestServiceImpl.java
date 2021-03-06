package io.github.bettersupport.lock.demo.service.impl;

import io.github.bettersupport.lock.core.annotation.GlobalSynchronized;
import io.github.bettersupport.lock.core.model.LockParam;
import io.github.bettersupport.lock.demo.model.Response;
import io.github.bettersupport.lock.demo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestServiceImpl implements TestService {
    private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);


    @Override
    @GlobalSynchronized(lockKey = "lock:test", timeOut = 1000L)
    public Response<String> testRequest(LockParam<String, Object> param) {
        long startTimestamp = System.currentTimeMillis();

        try {
            Thread.sleep(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTimestamp = System.currentTimeMillis();

        log.info("testRequest: Start: {} End: {}, param {},Started use {}ms", new Date(startTimestamp), new Date(endTimestamp), param.get("lockResult"), (endTimestamp - startTimestamp));

        return Response.buildResult("success");
    }

    @Override
    @GlobalSynchronized(lockKey = "lock:test", timeOut = 10000L)
    public Response<String> testRequest2(LockParam<String, Object> param) {
        long startTimestamp = System.currentTimeMillis();

        try {
            Thread.sleep(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTimestamp = System.currentTimeMillis();

        log.info("testRequest2: Start: {} End: {}, param {},Started use {}s", new Date(startTimestamp), new Date(endTimestamp), param.getLockResult(), (endTimestamp - startTimestamp)/1000);

        return Response.buildResult("success");
    }
}
