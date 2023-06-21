package com.jason.example.common.approve;

import com.jason.example.domain.LeaveRequest;
import lombok.Getter;
import lombok.Setter;

public abstract class ApproveHandler {
    @Getter
    @Setter
    protected ApproveHandler next;//下一个处理者

    public abstract void process(LeaveRequest leaveRequest);//处理请假

}
