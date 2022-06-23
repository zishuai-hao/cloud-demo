package com.example.cloudvertx.server;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONObject;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpServer {
    private final Vertx vertx;

    @PostConstruct
    public void initSHttp() {
        final Router router = Router.router(vertx);
        router.route().handler(context -> {
            // 远程地址
            final String address = context.request().connection().remoteAddress().toString();
            final MultiMap queryParams = context.queryParams();
            // 解析参数
            String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
            // 响应
            context.json(
                    new JsonObject()
                            .put("name", name)
                            .put("address", address)
                            .put("message", "Hello " + name + " connected from " + address)
            );
        });

        createHttpServer(router);
//        Handler<HttpServerRequest> staticHandler = req -> {
//            log.info("res: {}", req);
//            req.response()
//                    .putHeader("content-type", ContentType.JSON.toString())
//                    .end(new JSONObject().putOnce("name", "hzs").toString());
//        };
//        createHttpServer(staticHandler);
    }

    private void createHttpServer(Handler<HttpServerRequest> handler) {
        vertx.createHttpServer()
                .requestHandler(handler)
                .listen(8100)
                .onSuccess(server ->
                        System.out.println(
                                "HTTP server started on port " + server.actualPort()
                        )
                );
    }
}
