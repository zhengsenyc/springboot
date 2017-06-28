package org.zhengsen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhengsen.dao.GirlDao;
import org.zhengsen.entity.Girl;
import org.zhengsen.entity.User;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
@RestController
public class GirlController {
    @Autowired
    private User user;

    @Autowired
    private GirlDao girlDao;

    @RequestMapping("/hello")
    public String hello(){

        return "hello world!=="+user.getUsername()+"=="+user.getCupSize();
    }



    @GetMapping("/all")
    public List<Girl> findAll(){
        return girlDao.findAll();
    }

    @PostMapping("/{id}")
    public Girl seletOne(@PathVariable("id") Integer id){
        return girlDao.findOne(id);
    }

    /**
     * 新增
     */
    @PostMapping("/insert")
    public Girl insert(@Valid Girl girl, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result.getFieldError().getDefaultMessage());
            return null;
        }
        return girlDao.save(girl);
    }

    @PutMapping("/update")
    public Girl update(Girl girl){

        return girlDao.save(girl);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        girlDao.delete(id);
    }
	
	public void run1(){
		//hello
	}



}
