package vip.codehome.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author dsyslove@163.com
 * @createtime 2021/3/9--19:03
 * @description
 **/
public class RouteDemo {

  public static void main(String[] args) throws Exception {
    CamelContext context=new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("file:D:\\imgdata\\?noop=true").log("copy file:${file:name}").to("file:D:\\tmp\\");
      }
    });
    context.start();
    Thread.sleep(10000);
    context.stop();
  }
}
