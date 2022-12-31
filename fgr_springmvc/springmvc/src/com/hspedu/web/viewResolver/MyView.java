package com.hspedu.web.viewResolver;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */

/**
 * MyView继承了AbstractView  可以作为一个视图使用
 * 该视图会作为一个组件 注入到容器中 id为fgrView
 */
@Component(value = "fgrView")
public class MyView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //完成视图渲染 并决定要跳转的页面 [请求转发] /WEB-INF/pages/my_view.jsp
        System.out.println("进入自己的视图。。");

        //进行请求转发
        //  /WEB-INF/pages/my_view.jsp 会被springmvc解析
        httpServletRequest.getRequestDispatcher("/WEB-INF/pages/my_view.jsp").forward(httpServletRequest,httpServletResponse);
    }
}
