package com.info_system.service;

import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotifyZookeeperService {

    public void notify(String path, Map data) {
        ZkClient zk = new ZkClient("172.30.242.36:2182,172.30.242.37:2182,172.30.242.38:2182", 5000);
//        zk.createPersistent(path);
        String jsonstr = JSONObject.toJSONString(data);
        zk.writeData(path, jsonstr);
    }
}
