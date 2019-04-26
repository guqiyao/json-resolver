# json-resolver
是一个基于Spring mvc `HandlerMethodArgumentResolver`的实现, 用于解决在Post请求时, 
json数据体字段较少, 但为了接收参数却又要封装一个对象的问题;

当前版本: 1.0.1

## get it
### Maven
```
<dependency>
    <groupId>io.github.guqiyao</groupId>
    <artifactId>json-resolver</artifactId>
    <version>${version}</version>
</dependency>
```

## 使用
### spring boot
* **配置**
无需配置, 只要在classpath将会自动加载

* **使用**

```
@RestController
@RequestMapper("/test")
public class TestController {

    @PostMapping("/test_commit")
    public Object testPost(@JsonParameter name) {
        //... todo service logic
        
        return "OK";
    }
}
```