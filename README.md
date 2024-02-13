# review-application-microservices
Rahul Changes`
This is review application project using microservices, Spring MVC, Eureka, Hystrix and redis.

Microservices : User detail(Spring MVC) ---> Product service (Spring boot)
                                        ---> Review service (Spring boot)
					
Project Structure : User details microservice (Using Spring MVC) will display index page which will take user name and email as input and then the user detail microservice will call product microservice(Using spring boot) , get the list of products and will display it. User detail microservice will also connect with review microservices for read review and post review.


Below are the steps for Microservices and Spring MVC excluding Eureka, Hystrix and resdis.

Step 1 : Download three microservices from start.spring.io : Userdetailservice, productservice, reviewservice

step 2 : Add dependicies : User detail service dependencies 

                 <dependencies>
		 <dependency>
                          // We use this dependency for repository to connect to database
			  <groupId>org.springframework.boot</groupId>
			  <artifactId>spring-boot-starter-data-jpa</artifactId>
		 </dependency>
                 // under web dependency we get rest template also
		 <dependency>
			  <groupId>org.springframework.boot</groupId>
			  <artifactId>spring-boot-starter-web</artifactId>
		 </dependency>
                 <dependency>
			  <groupId>org.apache.tomcat.embed</groupId>
			  <artifactId>tomcat-embed-jasper</artifactId>
			  <scope>provided</scope>
		 </dependency>
	 
		<!-- jstl for jsp -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
		<!-- help our project to become client -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
    <dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
    // We use webflux dependency to enable web client in our project
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>
  
 Step 3 : Add product service dependencies :
 
        <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
    <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>
  
  step 3 : Add review service dependencies 
  
    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
    <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>
  
  Step 4 : Below are steps for user detail service
  
  Step 4.1 : For UserDetailServiceApplication class 
  
  4.1.1 Enable @SpringBootApplication annotation above UserDetailServiceApplication class
  4.1.2 Extend SpringBootServletInitializer and override its configure method.
  4.1.3 create a method to return rest template and add @Bean annotation above this method.
  4.1.4 Use command liner to put the data in the database as soon as the application starts.
  
  step 4.2 create Model folder --> keep all the model classes.
  
  Step 4.3 under controller folder --> UserDetailController class
  4.3.1 Enable @Controller annotation above UserDetailController class
  4.3.2 create method for all the required mappings.
  
  Step 4.4 Create repository folder --> under this keep all the repositories ,put @Repository annotation above repository class and extend it to CrudRepository
  
  step 4.5 Create service folder --> under it UserDetailService class
  4.5.1 Enable @Service annotation above UserDetailService class
  4.5.2 keep all the service method.
  4.5.3 To call productservice and reviewservice from UserDetailService, we will call like shown below
  List list = restTemplate.getForObject("http://localhost:8086/products",List.class)
  
  Review review = restTemplate.getForObject("http://localhost:8087/writereview/"+ id ,Review.class);
  
  restTemplate.postForObject("http://localhost:8087/savedreview", review ,Review.class);
  
  List<Review> reviews = restTemplate.getForObject("http://localhost:8087/getreview/"+id,List.class);
  
  step 4.6 under webapp--> WEB-INF -->Jsp --> Put all the Jsp files
  step 4.7 configure application.properties file
  
    spring.mvc.view.prefix: /WEB-INF/jsp/
    spring.mvc.view.suffix: .jsp

    server.port = 8085
    spring.application.name=userdetailservice

    spring.datasource.url=jdbc:mysql://localhost:3306/revapp_micro
    spring.datasource.username=root
    spring.datasource.password=mypass
  
    logging.level.org.springframework.web=DEBUG

    # Only below details are required for in memory DB

     spring.jpa.generate-ddl=true
    # spring.jpa.hibernate.ddl-auto=update
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.jpa.database=default
    spring.jpa.show-sql=true
  
 step 5:  Below are the steps for product service
 
  Step 5.1 : For ProductServiceApplication class
  5.1.1 Enable @SpringBootApplication annotation above ProductServiceApplication class
  
  step 5.2 create Model folder --> keep all the model classes.
  
  Step 5.3 under controller folder --> ProductController class
   5.3.1 Enable @RestController annotation above ProductController class
   5.3.2 create method for all the required mappings.
   
  Step 5.4 Create repository folder --> under this keep all the repositories, put @Repository annotation above repository class and extend it to CrudRepository
  
  step 5.5 Create service folder --> under it ProducService class
  5.5.1 Enable @Service annotation above UserDetailService class
  5.5.2 keep all the service method.
  
  step 5.6 configure application.properties file
  
        server.port = 8086
        spring.application.name=productservice

        spring.datasource.url=jdbc:mysql://localhost:3306/revapp_micro
        spring.datasource.username=root
        spring.datasource.password=mypass

        logging.level.org.springframework.web=DEBUG

        # Only below details are required for in memory DB

        spring.jpa.generate-ddl=true
         spring.jpa.hibernate.ddl-auto=update
       # spring.jpa.hibernate.ddl-auto=create
      spring.jpa.database=default
       spring.jpa.show-sql=true

step 6:  Below are the steps for Review service
same steps as Product Service

Step 7 : Adding Eureka

step 7.1 : download one project from start.spring.io, make it as Eureka server, To make it Eureka server
7.1.1 Add below dependencies 
 We get all these under Eureka dependencies. Eureka is a client as well as a server.
 
     <dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
    
7.1.2 Enable @SpringBootApplication and @EnableEurekaServer above EurekaServerApplication class.
7.1.2 Configure application.properties

    server.port=8761
     eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false

step 7.2 Make User detail service, product service and review service as Eureka client.
7.2.1  Enable @EnableEurekaClient annotation above UserDetailApplication, ProductServiceApplication and ReviewServiceApplication classes.
7.2.2 Enable @LoadBalanced annotation above getRestTemplate method in Application class.
7.2.3 Add below dependenices in all the three services to make them as Eureka client 

     <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
	

  
