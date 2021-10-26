package com.info_system.service;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ListenZookeeperService {
    private static Logger logger = LoggerFactory.getLogger(ListenZookeeperService.class);

    private static String listenPath = "/watcher";

    static {

        logger.error("start listen zookeeper");
        ZkClient client = new ZkClient("path", 5000);
        ZookeeperDataListenerImpl listener = new ZookeeperDataListenerImpl();
        client.subscribeDataChanges(listenPath, listener);//监听对应路径的值改变事件

    }
}
