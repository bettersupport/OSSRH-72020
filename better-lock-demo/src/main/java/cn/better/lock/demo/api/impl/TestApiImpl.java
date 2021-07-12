package cn.better.lock.demo.api.impl;

import cn.better.lock.core.model.LockParam;
import cn.better.lock.demo.api.TestApi;
import cn.better.lock.demo.model.Response;
import cn.better.lock.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiImpl implements TestApi {

    @Autowired
    private TestService testService;

    @Override
    public Response<String> testRequest(LockParam<String, String> param) {
        param.set("testKey", "4568453123132598sadasd12as1da5sd");
        return testService.testRequest(param);
    }
}
