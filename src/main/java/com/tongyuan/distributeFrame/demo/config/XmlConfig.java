package com.tongyuan.distributeFrame.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhangcy on 2018/2/28
 * 想使用QuartzService要加上classpath:quartz.xml
 */
@Configuration
@ImportResource(locations = {"classpath:redis.xml","classpath:quartz.xml"})
public class XmlConfig {
}
