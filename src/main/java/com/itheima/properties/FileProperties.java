package com.itheima.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    private String uploadPath;

    private String accessUrl;
}
