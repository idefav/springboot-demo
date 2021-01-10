package com.idefav.springbootdemo.properties;

import com.idefav.springbootdemo.loadbalancers.RandomLoadBancor;
import lombok.Data;

import java.util.List;

/**
 * the ServiceConfig description.
 *
 * @author ${USER}
 */
@Data
public class ServiceConfig {
    private List<String> serverList;
    private Class<?> loadBancor = RandomLoadBancor.class;
}
