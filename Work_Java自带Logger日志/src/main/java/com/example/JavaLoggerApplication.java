package com.example;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaLoggerApplication {

	public static final Logger log = Logger.getLogger(JavaLoggerApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(JavaLoggerApplication.class, args);

		// Logger的日志级别从高至低
		log.log(Level.SEVERE,  "严重信息");
		log.log(Level.WARNING, "警告信息");
		log.log(Level.INFO,    "一般信息");
		log.log(Level.CONFIG,  "设定方面的信息");
		log.log(Level.FINE,    "细微的信息");
		log.log(Level.FINER,   "更细微的信息");
		log.log(Level.FINEST,  "最细微的信息");

		// 另类写法
		// log.setLevel(Level.FINEST);
		// for (Handler handler : Logger.getLogger("com.example.JavaLoggerApplication").getHandlers()) {
		// 	handler.setLevel(Level.FINER);
		// }
		Logger.getLogger("com.example.JavaLoggerApplication").setLevel(Level.FINEST);

		log.severe("severe!");
		log.warning("warning!");
		log.info("info!");
		log.config("config!");
		log.fine("fine!");
		log.finer("finer!");
		log.finest("finest!");
	}

}
