package com.hspedu.web.json;

import com.hspedu.web.json.entity.Dog;
import com.hspedu.web.json.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Controller
@ResponseBody
public class JsonHandler {

    /**
     * @ResponseBody表示返回的数据是json格式
     * 底层根据目标方法先分析注解，根据http请求
     * 转换器HttpMessageConverter
     * @return
     */
    @RequestMapping("/json/dog")
//    @ResponseBody
    public Dog getJson(){
        //返回对象 转成json格式数据返回
        Dog dog = new Dog();
        dog.setAddress("小新");
        dog.setName("大黄狗");
        return dog;
    }

    //@RequestBody User user 形参上指定 springmvc会将提交的json字符串数据填充给指定的bean对象
    @RequestMapping(value = "/save2")
//    @ResponseBody
    public User save2(@RequestBody User user){
        System.out.println("user-" + user);
        return user;
    }

    //编写方法，以json格式数据返回多个Dog
    @RequestMapping("/json/dogs")
//    @ResponseBody
    public List<Dog> getJsons(){
        //返回对象 转成json格式数据返回
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("大黄狗","小窝"));
        dogs.add(new Dog("大黄狗2","小窝2"));
        return dogs;
    }

    //响应用户下载文件的请求
    @RequestMapping(value = "/downFile")
    public ResponseEntity<byte[]> downFile(HttpSession session)throws Exception{
//        //1.获取到下载文件的inputStream
//        InputStream resourceAsStream
//                = session.getServletContext().getResourceAsStream("/img/vpn.png");
//        //2.开辟存放文件的字节数组 使用byte[]  是可以支持二进制数据
//        byte[] bytes = new byte[resourceAsStream.available()];
//        //3.将下载的文件数据，读入到byte[]
//        resourceAsStream.read(bytes);
//        //4.创建返回的HttpStatus
//        HttpStatus httpStatus = HttpStatus.OK;
//        //创建headers
//        HttpHeaders headers = new HttpHeaders();
//        //指定返回的数据，客户端应当以附件形式处理
//        headers.add("Content-Disposition","attachment;filename=vpn.png");
//        //构建一个ResponseEntity 对象
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, httpStatus);

        InputStream resourceAsStream = session.getServletContext().getResourceAsStream("/img/vpn.png");
        byte[] bytes = new byte[resourceAsStream.available()];
        resourceAsStream.read(bytes);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename=vpn.png");
        HttpStatus httpStatus = HttpStatus.OK;

        /**
         * * @param body the entity body
         * 	 * @param headers the entity headers
         * 	 * @param status the status code
         */
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, httpStatus);
        return responseEntity;
    }
}
