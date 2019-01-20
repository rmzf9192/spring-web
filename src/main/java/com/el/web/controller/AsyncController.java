package com.el.web.controller;

import com.el.web.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @Auther: roman.zhang
 * @Date: 2019/1/8 14:20
 * @Version:V1.0
 * @Description:AsyncController
 */
@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<>((long) 3000, "create fail...");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/getResult")
    public String getResult(){
        //创建订单
        String string = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(string);
        return "success=>"+string;
    }
    /**
     * 1、控制器返回Callable
     * 2、Spring异步处理，将Callable 提交到 TaskExecutor 使用一个隔离的线程进行执行
     * 3、DispatcherServlet和所有的Filter退出web容器的线程，但是response 保持打开状态；
     * 4、Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理；
     * 5、根据Callable返回的结果。SpringMVC继续进行视图渲染流程等（从收请求-视图渲染）。
     *
     * preHandle.../spring-web/async01
     主线程开始...Thread[http-bio-8081-exec-3,5,main]==>1513932494700
     主线程结束...Thread[http-bio-8081-exec-3,5,main]==>1513932494700
     =========DispatcherServlet及所有的Filter退出线程============================

     ================等待Callable执行==========
     副线程开始...Thread[MvcAsync1,5,main]==>1513932494707
     副线程开始...Thread[MvcAsync1,5,main]==>1513932496708
     ================Callable执行完成==========

     ================再次收到之前重发过来的请求========
     preHandle.../spring-web/async01
     postHandle...（Callable的之前的返回值就是目标方法的返回值）
     afterCompletion...

     异步的拦截器:
     1）、原生API的AsyncListener
     2）、SpringMVC：实现AsyncHandlerInterceptor；
     * @return
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01(){
        System.out.println("主线程开始："+Thread.currentThread().getName()+"执行时间："+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("副线程开始：" + Thread.currentThread().getName() + "执行时间：" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("副线程结束：" + Thread.currentThread().getName() + "执行时间：" + System.currentTimeMillis());
                return "Callable<String> async011()";
            }
        };

        System.out.println("主线程结束："+Thread.currentThread().getName()+"执行时间："+System.currentTimeMillis());
        return callable;
    }
    private SseEmitter sseEmitter=new SseEmitter();
    private DeferredResult<String> deferredResult=new DeferredResult<>();
    /**
     * Callback和DeferredResult用于设置单个结果,如果有多个结果需要返回给客户端时，可以使用SseEmitter以及ResponseBodyEmitter等；
     */
    //返回SseEmitter对象
    @RequestMapping("/testSseEmitter")
    public SseEmitter testSseEmitter(){
        return new SseEmitter();
    }

    /**
     * 向SseEmitter发送数据
     * @return
     */
    @RequestMapping("/setSseEmitter")
    public String setSseEmitter(){
        try {
            sseEmitter.send(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    /**
     * 第一步访问：http://localhost/test/testSseEmitter
     * 第二步连续访问：http://localhost/test/setSseEmitter
     * 第三步访问：http://localhost/test/completeSseEmitter
     * 可以看到结果，只有当第三步执行后，第一步的访问才算结束。
     * @return
     */
    //将SseEmitter对象设置完成
    @RequestMapping("/completeSseEmitter")
    public String completeSseEmitter(){
        sseEmitter.complete();
        return "success";
    }
    /**
     * 用于直接将结果写出到Response的OutputStream中； 如文件下载等，
     */
    @GetMapping("/download")
    public StreamingResponseBody handle(){
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                System.out.println("执行了");
            }
        };
    }
}
