package vip.codehome.camel.udp;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author dsyslove@163.com
 * @createtime 2021/3/9--20:01
 * @description
 **/
public class UdpDemo {

  public static void main(String[] args) throws Exception {
    CamelContext context=new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("netty:udp://127.0.0.1:8111?sync=false").logMask().to("log:vip");
        from("direct:start").to("log:vip.codehome?logMask=true");
      }
    });
    context.start();

    //Thread.sleep(10000);
   // context.stop();
  }
}
