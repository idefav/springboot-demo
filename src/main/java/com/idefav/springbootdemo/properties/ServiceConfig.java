package com.idefav.springbootdemo.properties;

import com.idefav.springbootdemo.Loadbancor;
import com.idefav.springbootdemo.RandomLoadBancor;
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
