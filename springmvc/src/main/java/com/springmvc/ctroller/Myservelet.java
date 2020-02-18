package com.springmvc.ctroller;


import com.springmvc.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//@RequestMapping("/haha")//为这个类的所有方法指定基本路径
@Controller
@SessionAttributes(value = "map")//只能作用于类级别，给请求域中的数据也给session中一份,只能是key=map的数据
public class Myservelet {

    @RequestMapping("/hello")
    public String doget(){
        System.out.println("我正在处理请求");
        return "sussed";
    }

    @RequestMapping("/user/{id}")
    public String pathvar(@PathVariable("id") String id){
        System.out.println(id);
        return "sussed";

    }

    //基于Rest风格的增删改查
    @RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
    public String chaxun(@PathVariable("id")String id){
        System.out.println("查询"+id+"号图书");
        return "sussed";
    }

    @RequestMapping(value = "/book/{id}",method = RequestMethod.PUT)
    public String gengxin(@PathVariable("id")String id){
        System.out.println("更新了"+id+"号图书");
        return "sussed";
    }

    @RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    public String shanchu(@PathVariable("id")String id){
        System.out.println("删除了"+id+"号图书");
        return "sussed";
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public String charu(){
        System.out.println("插入了新的图书");
        return "sussed";
    }

    //获取请求参数
    @RequestMapping("/canshu")//如果不使用@RequestParam就必须要求函数参数名字和请求参数名字一致
    public String canshu(@RequestParam(value = "user",required = true,defaultValue = "sb") String user, @RequestHeader("User-Agent")String UserAgent
                        ,@CookieValue(value = "JSESSIONID",required = false)String jid){
        System.out.println(user);
        System.out.println(UserAgent);
        System.out.println(jid);
        return "sussed";
    }

    //接受请求参数并封装成对象
    @RequestMapping(value = "/shu",method = RequestMethod.POST)
    public String duixiang(Book book){
        System.out.println(book);
        return "sussed";
    }

    //springmvc支持方法传入原生api
    @RequestMapping("/api")
    public String api(HttpServletRequest request, HttpSession session){
        request.setAttribute("a1","我是请求属性啊");
        session.setAttribute("a","我是回话属性啊");
        return "api";
    }

    //不用原生api，设置请求域中的参数,session在类级别设置
    @RequestMapping("/noapi")
    public String noapi(Map<String,Object>map, Model model, ModelMap modelMap){
        map.put("map","好好");
        model.addAttribute("model","我是model");
        modelMap.addAttribute("modelmap", "我是modelmap");
        return "api";
    }

    //方法返回值可以用modelandview代替，他既可以存页面也可以存数据
    @RequestMapping("/mav")
    public ModelAndView mav(){
        ModelAndView api = new ModelAndView("api");
        api.addObject("mav", "我是用mav获得的参数");
        return api;
    }

    //利用modelattribute属性，全字段修改
    //目标方法用来保存图书
    @RequestMapping("/ma")
    public String updatebook(@ModelAttribute("book")Book book){
        System.out.println(book);
        return "sussed";

    }

    //调用这个类的任何方法,都得先调用它,并且这个类中的BindingAwareMqdelMap是共享的
    @ModelAttribute
    public void zhaoshu(ModelMap map) {
        Book book = new Book();
        book.setName("西游记");
        book.setPrice(12);
        map.addAttribute("book", book);
        System.out.println("我是你的modelattribute");
    }

    //利用前缀,让视图解析器不进行拼串
    @RequestMapping("/qian1")
    public String qian1(){
        return "../../hello";
    }
    @RequestMapping("/qian2")
    public String qian2(){
        return "forward:hello.jsp";
    }@RequestMapping("/qian3")
    public String qian3(){
        return "forward:/qian2";
    }@RequestMapping("/qian4")
    public String qian4(){
        return "redirect:hello.jsp";
    }@RequestMapping("/qian5")
    public String qian5(){
        return "redirect:/qian4";
    }

}
