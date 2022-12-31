package com.hspedu.web.datavalid;

import com.hspedu.web.datavalid.entity.Monster;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0 //处理器响应用户提交数据
 * @Scope(value = "prototype")  每次请求生成一个新对象
 */
@Controller
@Scope(value = "prototype")
public class MonsterHandler {
    /**
     * @param map 当我们向map添加数据时，会默认存放到request域中
     * @return
     */
    @RequestMapping(value = "/addMonsterUI")
    public String addMonsterUI(Map<String, Object> map) {
        /**老韩解读:
         1. 这里的表单，我们使用 springMVC 的标签来完成
         2. SpringMVC 表单标签在显示之前必须在 request 中有一个 bean, 该 bean
         的属性和表单标签的字段要对应!
         request 中的 key 为: form 标签的 modelAttrite 属性值， 比如这里的
         monsters
         3. SpringMVC 的 form:form 标签的 action 属性值中的 / 不代表 WEB 应用
         的根目录. 4. <form:form action="monster" method="POST" modelAttribute="monster">
         // 这 里 需 要 给 request 增 加 一 个 monster ， 因 为 jsp 页 面 的
         modelAttribute="monster"需要
         //这时是 springMVC 的内部的检测机制 即使是一个空的也需要，否则报错.
         */
        //跳转的页面使用了springmvc标签 姐u需要准备一个对象  放入request对象中
        // 对象的属性名monster，对应表单的modelAttribute值
        map.put("monster", new Monster());
        return "datavalid/monster_addUI";
    }

    //编写方法，处理添加妖怪 提交的数据按照参数名和属性名匹配 直接封装到对象中
    //@Valid Monster monster 对monster数据行进校验
    //Errors errors 骄傲眼 出现错误会将错误信息保存error
    //Map<String,Object> map 保留校验错误的信息 同时保存monster对象
    //校验发生的时机 在springmvc底层 反射调用目标方法时 会接收到http请求的数据，根据注解进行验证
    // 在验证过程中 出现错误信息 填充到error和map
    @RequestMapping(value = "/save")
    public String save(@Valid Monster monster, Errors errors,Map<String,Object> map){
        System.out.println("monster=" + monster);
        System.out.println("====map=====");
        for (Map.Entry<String,Object> entry : map.entrySet()){
            System.out.println("key=" + entry.getKey());
            System.out.println("key=" + entry.getValue());
        }
        System.out.println("=====Errors=====");
        if (errors.hasErrors()){//判断是否有错误
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println("error=" + error);
            }
            //返回添加页面
            return "datavalid/monster_addUI";
        }

        return "/datavalid/success";
    }


//    //取消绑定monster的name表单提交的值给monster.name属性
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder){
//        /**
//         * 1.方法上需要标注 @InitBinder  springmvc会初始化webDataBinder
//         * 2.webDataBinder.setDisallowedFields 表示指定属性的绑定
//         * 表单提交字段为name时  ，就不再接收到name值，填充到model数据到monster的name属性
//         * springmvc在底层通过反射调用目标方法时，会接收到http的参数和值（反射和注解）取消对指定属性的填充
//         * 支持可变参数，多个参数，如果取消某字段绑定，验证无意义，应当把验证的注解去掉
//         * //    @NotEmpty//不能为空
//         *     private String name;
//         *     name属性会使用它的默认值
//         */
//        webDataBinder.setDisallowedFields("name");
//    }
}
