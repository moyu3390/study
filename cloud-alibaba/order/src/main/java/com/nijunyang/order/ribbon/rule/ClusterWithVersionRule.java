package com.nijunyang.order.ribbon.rule;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 集群和版本规则
 * Created by nijunyang on 2020/12/14 21:36
 */
@Slf4j
public class ClusterWithVersionRule extends AbstractLoadBalancerRule {


    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    /**
     * 重写choose方法
     * @param key
     * @return
     */
    @SneakyThrows
    @Override
    public Server choose(Object key) {
        //获取服务配置的集群名
        String clusterName = nacosDiscoveryProperties.getClusterName();
        //当前的版本号 配置文件配置的metadata信息
        String currentVersion = nacosDiscoveryProperties.getMetadata().get("version");
        //获取负载均衡器
        BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) getLoadBalancer();
        //调用服务的名字
        String invokedServerName = baseLoadBalancer.getName();
        //获取namingServer（包含nacos注册发现相关api）
        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
        //获取被调用的服务的所有实例
        List<Instance> invokedAllInstanceList = namingService.getAllInstances(invokedServerName);
        //同集群同版本
        List<Instance> theSameClusterAndVersionList = new ArrayList<>();
        //跨集群同版本
        List<Instance> theSameVersionList = new ArrayList<>();
        for (Instance instance : invokedAllInstanceList) {
            if (clusterName.equalsIgnoreCase(instance.getClusterName())
            && currentVersion.equalsIgnoreCase(instance.getMetadata().get("version"))) {
                theSameClusterAndVersionList.add(instance);
            } else if (currentVersion.equalsIgnoreCase(instance.getMetadata().get("version"))) {
                theSameVersionList.add(instance);
            }
        }
        Instance invokedInstance;
        if (theSameClusterAndVersionList.isEmpty()) {
            //跨集群同版本调用, 随机选一个
            if (theSameVersionList.isEmpty()) {
                throw new RuntimeException("无对应版本服务");
            }
            SecureRandom random = new SecureRandom();
            int i = random.nextInt(theSameVersionList.size());
            invokedInstance = theSameVersionList.get(i);
        } else {
            //同集群同版本调用 随机选一个
            SecureRandom random = new SecureRandom();
            int i = random.nextInt(theSameClusterAndVersionList.size());
            invokedInstance = theSameClusterAndVersionList.get(i);
        }
        return new NacosServer(invokedInstance);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
