package com.stackroute.pie.configuration;


import com.stackroute.pie.domain.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

import java.util.Map;
@EnableKafka
@Configuration
public class Kafkaconfiguration {

    String localhost = "localhost:9092";




    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Insured> insuredConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_insured_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Insured.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Insured> insuredKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Insured> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(insuredConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Insurer> insurerConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_insurer_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Insurer.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Insurer> insurerKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Insurer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(insurerConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Policy> policyConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_policy_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Policy.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Policy> policyKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Policy> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(policyConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, FamilyMembers> familyConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,localhost);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_family_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(FamilyMembers.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FamilyMembers> familyKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FamilyMembers> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(familyConsumerFactory());
        return factory;
    }

}
