package br.com.project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ITopic;

import br.com.project.canonical.NotifyCanonical;
import br.com.project.event.Event;
import br.com.project.event.listener.NotifyListener;

@Configuration
public class HazelcastConfiguration {

	private final String hazelcastInstanceName;
	private final String host;
	private final Integer port;
	private final String queueNotify;
	private final String topicNotify;
	
	public HazelcastConfiguration(
			@Value("${hazelcast.configuration.instance.name:hazelcast-solution}") final String hazelcastInstanceName, //
			@Value("${hazelcast.configuration.network.host:localhost}") final String host, //
			@Value("${hazelcast.configuration.network.port:9910}") final Integer port, //
			@Value("${hazelcast.configuration.queue.name.notify:notifyQueue}") final String queueNotify, //
			@Value("${hazelcast.configuration.topic.name.notify:notifyTopic}") final String topicNotify ) {
		this.hazelcastInstanceName = hazelcastInstanceName;
		this.host = host;
		this.port = port;
		this.queueNotify = queueNotify;
		this.topicNotify = topicNotify;
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean(name = "hazelcastInstance", destroyMethod = "shutdown")
	public HazelcastInstance hazelcastInstance() {
		return Hazelcast.getOrCreateHazelcastInstance(hazelcastConfig());
	}
	/**
	 * 
	 * @return
	 */
	public Config hazelcastConfig() {
		final Config config = new Config(hazelcastInstanceName);
		config.getGroupConfig().setName(hazelcastInstanceName);
		config.setNetworkConfig(getNetworkConfig());
		config.addTopicConfig(getNotifyTopicConfig());

		return config;
	}
	/**
	 * 
	 * @return
	 */
	private TopicConfig getNotifyTopicConfig() {
		return new TopicConfig(topicNotify);
	}
	/**
	 * 
	 * @return
	 */
	private NetworkConfig getNetworkConfig() {
		final NetworkConfig networkConfig = new NetworkConfig();
		networkConfig.setPort(port);
		networkConfig.setPortAutoIncrement(true);
		networkConfig.setJoin(getJoinConfig());
		return networkConfig;
	}
	/**
	 * 
	 * @return
	 */
	private JoinConfig getJoinConfig() {
		final JoinConfig joinConfig = new JoinConfig();
		joinConfig.setMulticastConfig(getMulticastConfig());
		joinConfig.setTcpIpConfig(tcpIpConfig());
		return joinConfig;
	}
	/**
	 * 
	 * @return
	 */
	private TcpIpConfig tcpIpConfig() {
		final TcpIpConfig tcpIpConfig = new TcpIpConfig();
		tcpIpConfig.setEnabled(true);
		tcpIpConfig.addMember(host);
		return tcpIpConfig;
	}
	/**
	 * 
	 * @return
	 */
	private MulticastConfig getMulticastConfig() {
		final MulticastConfig multicastConfig = new MulticastConfig();
		multicastConfig.setEnabled(false);
		return multicastConfig;
	}
	/**
	 * 
	 * @param hazelcastInstance
	 * @return
	 */
	@Bean
	public IQueue<NotifyCanonical> notifyUpdateOrderQueue(final HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getQueue(queueNotify);
	}
	/**
	 * 
	 * @param hazelcastInstance
	 * @param listener
	 * @return
	 */
	@Bean
	public ITopic<Event<NotifyCanonical>> topic(final HazelcastInstance hazelcastInstance, final NotifyListener listener) {
		final ITopic<Event<NotifyCanonical>> topic = hazelcastInstance.getTopic(topicNotify);
		topic.addMessageListener(listener);
		return topic;
	}
}

