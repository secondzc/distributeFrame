package com.tongyuan.distributeFrame.demo.check.strategyImpl;

import com.tongyuan.distributeFrame.demo.check.FlowStrategy;
import com.tongyuan.distributeFrame.demo.check.enums.FlowStatus;
import com.tongyuan.distributeFrame.demo.dao.RequestMapper;
import com.tongyuan.distributeFrame.demo.dao.RoleMapper;
import com.tongyuan.distributeFrame.demo.dao.RoleRequestMapper;
import com.tongyuan.distributeFrame.demo.entity.Request;
import com.tongyuan.distributeFrame.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangcy on 2018/3/17
 */
public abstract  class AbstractStrategy implements FlowStrategy{

    @Autowired
    protected RequestMapper requestMapper;
    @Autowired
    protected RoleMapper roleMapper;
    @Autowired
    protected RoleRequestMapper roleRequestMapper;

    @Override
    public void start(Long requestId) {
        //设置第一个节点为active
        String firstRoleName = getFirstRoleName();
        Role role = roleMapper.selectByRoleName(firstRoleName);
        roleRequestMapper.setActive(requestId,role.getId());
    }

    @Override
    public void agree(Long requestId) {
        //设置本节点状态为agree
        Request request = requestMapper.queryById(requestId);
        String roleName = request.getCurrentRole();
        Role role = roleMapper.selectByRoleName(roleName);
        roleRequestMapper.setAgree(requestId,role.getId());
        //查找下一节点roleRequest，若没有，设置流程成功，若还有，设置下一节点为current并设为active
        String nextRoleName = getNextRoleName(role.getRoleName());
        if(null == nextRoleName){//流程成功
            requestMapper.setStatus(FlowStatus.Success.getStatus(),requestId);
        }else{//流程还有后续
            requestMapper.setCurrentRole(nextRoleName,requestId);
            Role nextRole = roleMapper.selectByRoleName(nextRoleName);
            roleRequestMapper.setActive(requestId,nextRole.getId());
        }
    }

    @Override
    public void disagree(Long requestId) {
        //设置本节点状态为disagree
        Request request = requestMapper.queryById(requestId);
        String roleName = request.getCurrentRole();
        Role role = roleMapper.selectByRoleName(roleName);
        roleRequestMapper.setDisagree(requestId,role.getId());
        //流程设置为失败
        requestMapper.setStatus(FlowStatus.UnSuccess.getStatus(),requestId);
    }

    abstract public String getFirstRoleName();
    abstract public String getNextRoleName(String roleName);
}
