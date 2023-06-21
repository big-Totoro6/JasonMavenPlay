package com.jason.example.common.approve;

import com.jason.example.domain.LeaveRequest;

public class GMHandler extends ApproveHandler{
    @Override
    public void process(LeaveRequest leaveRequest) {
        //员工在公司工龄超过2年,则审批通过
        if(leaveRequest.workingAge() >=2 && leaveRequest.numOfDays() > 3){
            System.out.println(leaveRequest.name()+",你通过总经理审批!");
            if(null != next){
                next.process(leaveRequest);
            }
        }else {
            System.out.println("在公司年限不够,长假未通过总经理审批!");
            return;
        }
    }
}
