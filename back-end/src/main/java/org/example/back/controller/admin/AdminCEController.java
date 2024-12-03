package org.example.back.controller.admin;

import jakarta.annotation.Resource;
import org.example.back.common.Response;
import org.example.back.common.request.RegisterRequest;
import org.example.back.entity.ComprehensiveEvaluation;
import org.example.back.entity.User;
import org.example.back.service.AdminCEService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title AdminCEController
 * @description <TODO description class purpose>
 * @author Canary
 * @creat 2024/11/14 上午12:29
 * @version 1.0.0
 **/

@RestController
@RequestMapping("/admin/ce")
public class AdminCEController {

    @Resource
    private AdminCEService adminCEService;

    //<TODO 添加综测记录接口>
    @PostMapping("/addComprehensiveEvaluation")
    public Response<String> addComprehensiveEvaluation(@RequestBody ComprehensiveEvaluation comprehensiveEvaluation) {
        try{
            adminCEService.addComprehensiveEvaluation(comprehensiveEvaluation);
            return Response.success();
        }catch (Exception e){
            return Response.internalServerError();
        }
    }

    //<TODO 删除综测记录接口>
    @PostMapping("/deleteComprehensiveEvaluation")
    public Response<String> deleteComprehensiveEvaluation(@RequestBody ComprehensiveEvaluation comprehensiveEvaluation) {
        try{
            adminCEService.deleteComprehensiveEvaluation(comprehensiveEvaluation);
            return Response.success();
        }catch (Exception e){
            return Response.internalServerError();
        }
    }


    //<TODO 修改综测记录接口>
    @PostMapping("/updateComprehensiveEvaluation")
    public Response<String> updateComprehensiveEvaluation(@RequestBody ComprehensiveEvaluation comprehensiveEvaluation) {
        try{
            adminCEService.updateComprehensiveEvaluation(comprehensiveEvaluation);
            return Response.success();
        }catch (Exception e){
            return Response.internalServerError();
        }
    }


    //<TODO 查询所有综测记录接口>

}
