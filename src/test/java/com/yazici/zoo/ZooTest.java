package com.yazici.zoo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;


public class ZooTest {
    private static final Logger LOG = LoggerFactory.getLogger(ZooTest.class);
    private CuratorFramework framework;

    @BeforeMethod
    public void setUp() throws Exception {
        final RetryPolicy policy = new RetryUntilElapsed(100, 10);
        framework = CuratorFrameworkFactory.newClient("localhost:2181", policy);
        framework.start();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        framework.close();
    }

    @Test
    public void shouldCreateZnode() throws Exception {
//        framework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/kerem/v1/myService/instance1");

        List<String> result = framework.getChildren().forPath("/kerem");
        LOG.info("====================list /====================");
        for (String s : result) {
            LOG.info(s);
        }
        LOG.info("========================================");
    }

//    @Test
    public void shouldCreateZnodeWithGivenData() throws Exception {
        String result = framework.create().forPath("/ulku", "ulku - data".getBytes());
        LOG.info("==================create /ulku======================");
        LOG.info(result);
        LOG.info("========================================");
    }
}
