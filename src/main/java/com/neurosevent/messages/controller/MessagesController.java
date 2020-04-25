package com.neurosevent.messages.controller;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neurosevent.messages.config.KafkaProperties;
import com.neurosevent.messages.dto.MessageDTO;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

	private final Logger log = LoggerFactory.getLogger(MessagesController.class);

	private final KafkaProperties kafkaProperties;
	private KafkaProducer<String, String> producer;
	private ExecutorService sseExecutorService = Executors.newCachedThreadPool();

	public MessagesController(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
		this.producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
	}

	@PostMapping("/publish/{topic}")
	public PublishResult publish(@PathVariable String topic, @RequestBody MessageDTO messageDTO)
			throws ExecutionException, InterruptedException {
		log.debug("REST request to send to Kafka topic {} from {} the message : {}", topic, messageDTO.getPublisher(),
				messageDTO.getPayload());
		RecordMetadata metadata = producer.send(new ProducerRecord<>(topic, messageDTO.getPayload())).get();
		return new PublishResult(metadata.topic(), metadata.partition(), metadata.offset(),
				Instant.ofEpochMilli(metadata.timestamp()));
	}

	private static class PublishResult {
		public final String topic;
		public final int partition;
		public final long offset;
		public final Instant timestamp;

		private PublishResult(String topic, int partition, long offset, Instant timestamp) {
			this.topic = topic;
			this.partition = partition;
			this.offset = offset;
			this.timestamp = timestamp;
		}
	}

}