package br.com.project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

import br.com.project.canonical.NotifyCanonical;
import br.com.project.event.listener.ReciptionNotifyListener;

@Configuration
public class HazelcastConfiguration {

	private final String hazelcastInstanceName;
	private final String host;
	private final Integer port;
	private final String queueNotify;
	
	public HazelcastConfiguration(
			@Value("${hazelcast.configuration.instance.name:linx-mail-service}") final String hazelcastInstanceName,
			@Value("${hazelcast.configuration.network.host:localhost}") final String host,
			@Value("${hazelcast.configuration.network.port:9919}") final Integer port,
			@Value("${hazelcast.configuration.queue.name.notify:notifyQueue}") final String queueNotify) {
		this.hazelcastInstanceName = hazelcastInstanceName;
		this.host = host;
		this.port = port;
		this.queueNotify = queueNotify;
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
		config.addQueueConfig(notifyUpdateOrderQueueConfig());
		return config;
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
	private MulticastConfig getMulticastConfig() {
		final MulticastConfig multicastConfig = new MulticastConfig();
		multicastConfig.setEnabled(false);
		return multicastConfig;
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
	private QueueConfig notifyUpdateOrderQueueConfig() {
		return new QueueConfig(queueNotify);
	}
	/**
	 * 
	 * @param hazelcastInstance
	 * @param reciptionListener
	 * @return
	 */
	@Bean
	public IQueue<NotifyCanonical> queue(final HazelcastInstance hazelcastInstance, final ReciptionNotifyListener reciptionListener) {
		final IQueue<NotifyCanonical> mailQueue = hazelcastInstance.getQueue(queueNotify);
		final Boolean includeValue = Boolean.FALSE;
		mailQueue.addItemListener(reciptionListener, includeValue);
		return mailQueue;
	}
}
