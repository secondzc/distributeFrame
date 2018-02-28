package com.tongyuan.distributeFrame.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhangcy on 2018/2/28
 *
 */
@Configuration
@ImportResource(locations = {"classpath:redis.xml"})
public class XmlConfig {
}
