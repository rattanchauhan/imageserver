package com.imageserver.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.imageserver.app"})
@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ImageServerApplication {

	public static void main(String[] args) throws UnknownHostException {
      SpringApplication app = new SpringApplication(ImageServerApplication.class);
      Environment env = app.run(args).getEnvironment();
      String protocol = "http";
      if (env.getProperty("server.ssl.key-store") != null) {
          protocol = "https";
      }
      log.info("\n----------------------------------------------------------\n\t" +
              "Application '{}' is running! Access URLs:\n\t" +
              "Local: \t\t{}://localhost:{}\n\t" +
              "External: \t{}://{}:{}\n\t" +
              "Profile(s): \t{}\n----------------------------------------------------------",
          env.getProperty("spring.application.name"),
          protocol,
          env.getProperty("server.port"),
          protocol,
          InetAddress.getLocalHost().getHostAddress(),
          env.getProperty("server.port"),
          env.getActiveProfiles());
	}
}
