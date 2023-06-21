package com.jason.example.common.approve;

import com.jason.example.domain.LeaveRequest;

public class PMHandler extends ApproveHandler{
    @Override
    public void process(LeaveRequest leaveRequest) {
        //未填写姓名的请假单不通过
        if(null != leaveRequest.name()){
            if(leaveRequest.numOfDays() <= 3){
                System.out.println(leaveRequest.name()+",你通过项目经理审批!");
            }else {
                System.out.println("项目经理转交总经理");
                if(null != next){
                    next.process(leaveRequest);
                }
            }
        }else {
            System.out.println("请假单未填写完整,未通过项目经理审批!");
            return;
        }
    }
}
