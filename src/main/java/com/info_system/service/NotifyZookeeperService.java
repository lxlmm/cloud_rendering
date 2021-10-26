package com.info_system.service;

import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotifyZookeeperService {

    public void notify(String path, Map data) {
        ZkClient zk = new ZkClient("path", 5000);
//        zk.createPersistent(path);
        String jsonstr = JSONObject.toJSONString(data);
        zk.writeData(path, jsonstr);
    }
}
