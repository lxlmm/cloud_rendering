package com.info_system.service;

import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperDataListenerImpl implements IZkDataListener {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperDataListenerImpl.class);

    public void handleDataChange(String s, Object o) throws Exception {
        logger.error("listen path->" + s);
        logger.error("listen object->" + o);
    }

    public void handleDataDeleted(String s) throws Exception {
        logger.error("s->" + s);
    }
}
