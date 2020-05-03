package com.example.auth.config;

import com.example.auth.utils.HTTPUtils;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.Optional;


@Configuration
@Profile("dev")
@EnableMongoRepositories(value = {"com.niyo.transaction.repository"})
@Import(value = MongoAutoConfiguration.class)
@Slf4j
public class DatabaseConfiguration extends AbstractMongoConfiguration {

  @Inject
  private MongoProperties mongoProperties;

  @Inject
  private MongoClient mongoClient;

  @Inject
  private Mongo mongo;

  @Inject
  private Environment environment;

  @Bean
  public ValidatingMongoEventListener validatingMongoEventListener() {
    return new ValidatingMongoEventListener(validator());
  }

  @Bean
  @Override
  public MongoCustomConversions customConversions() {
    return new MongoCustomConversions(HTTPUtils.getConverters());
  }

  public Mongo mongo() {
    return mongo;
  }

  @Override
  protected String getDatabaseName() {
    return mongoProperties.getDatabase();
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
    return new LocalValidatorFactoryBean();
  }

  @Override
  public MongoClient mongoClient() {

    return mongoClient;
  }

  @Bean(name = "auditingDateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now());
  }

  @Bean(name = "mongoTemplate")
  @Override
@Profile("dev")
//@Profile(Constants.SPRING_PROFILE_PRODUCTION)
  public MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate = new MongoTemplate(this.mongoDbFactory(), this.mappingMongoConverter());
    mongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
    mongoTemplate.setReadPreference(com.mongodb.ReadPreference.primary());
    return mongoTemplate;
  }
}
