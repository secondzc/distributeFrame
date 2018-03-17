package com.tongyuan.distributeFrame.demo.check.strategyImpl;

import com.tongyuan.distributeFrame.demo.check.enums.RoleEnum;
import com.tongyuan.distributeFrame.util.DataUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Created by zhangcy on 2018/3/17
 * 热力学工程师最先审签，然后在机械和电子工程师中随机选择一个进行审签
 */
public class HyFirstStrategy extends AbstractStrategy{
    @Override
    public String getFirstRoleName() {
        return RoleEnum.HydraulicEngineer.getEngineerRole();
    }

    @Override
    public String getNextRoleName(String roleName) {
        if(StringUtils.isEmpty(roleName)){
            return null;
        }
        if(StringUtils.equals(RoleEnum.HydraulicEngineer.getEngineerRole(),roleName)){
            if(new Random().nextInt(2)==0){
                return RoleEnum.MachineEngineer.getEngineerRole();
            }else{
                return RoleEnum.ElectricEngineer.getEngineerRole();
            }
        }
        return null;
    }
}
