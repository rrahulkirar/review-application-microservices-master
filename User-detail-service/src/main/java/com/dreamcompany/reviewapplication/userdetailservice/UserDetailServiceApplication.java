package com.dreamcompany.reviewapplication.userdetailservice;

import com.dreamcompany.reviewapplication.userdetailservice.model.Product;
import com.dreamcompany.reviewapplication.userdetailservice.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableCaching
public class UserDetailServiceApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UserDetailServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserDetailServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}



	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, List<com.dreamcompany.reviewapplication.productservice.model.Product>> redisTemplate() {
		RedisTemplate<String, List<com.dreamcompany.reviewapplication.productservice.model.Product>> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	SimpleDateFormat obj = new SimpleDateFormat("yyyy/MM/dd");

	@Bean
	public CommandLineRunner getCommandLineRunner(ProductRepo productRepo) {

		return (args) -> {

			Product product1 = new Product("Dettol", "Handwash", obj.parse("2022/05/21"));
			Product product2 = new Product("Vim", "Dishwasher", obj.parse("2023/02/12"));
			Product product3 = new Product("Bambino", "Pasta", obj.parse("2025/01/23"));
			Product product4 = new Product("Solimo", "Water Bottle", obj.parse("2023/07/23"));
			Product product5 = new Product("Pigeon", "Lighter", obj.parse("2023/07/23"));
			Iterable<Product> products = productRepo.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
			List product = new ArrayList();
			products.iterator().forEachRemaining(l->product.add(l));
            if(redisTemplate().opsForHash().hasKey("cachelist","key2"))
			{
				redisTemplate().opsForHash().put("cachelist","key2",product);
			}

		};
	}
	}

