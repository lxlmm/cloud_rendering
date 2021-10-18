package com.info_system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void exec(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),
                    "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();
            logger.error("exec python result:" + re);
        } catch (Exception e) {
            logger.error("exec python error:", e);
        }
    }
}
