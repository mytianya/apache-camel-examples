package vip.codehome.camel.hawtio;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author dsyslove@163.com
 * @createtime 2021/3/9--20:01
 * @description
 **/
@Component
public class UdpDemo implements ApplicationRunner {

  public static void main(String[] args) throws Exception {

  }
  private static String udpEnpoint(String udpIp,int udpPort){
    return new StringBuilder("netty:udp://")
    .append(udpIp).append(":").append(udpPort)
        .append("?sync=false&decoder=#myDecoder&encoder=#myEncoder").toString();
  }
  private static String kafkaEnpoint(){
    return new StringBuilder("kafka:IIS_TEST?brokers=192.28.4.23:9092,192.28.4.24:9092,192.28.4.25:9092&serializerClass=org.apache.kafka.common.serialization.ByteArraySerializer")
        .toString();
  }

  public void run(ApplicationArguments args) throws Exception {
    SimpleRegistry registry=new SimpleRegistry();
    registry.put("myEncoder",new StringEncoder());
    registry.put("myDecoder",new StringDecoder());
    CamelContext context=new DefaultCamelContext(registry);
    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from(udpEnpoint("127.0.0.1",8111))
         //   .to(kafkaEnpoint());
        .to("stream:out");
      }
    });
    context.start();
  }
}
