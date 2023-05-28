/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.congomall.bff.biz.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.opengoofy.congomall.bff.biz.common.SentinelLimitFlowConstant.ADD_ADDRESS_PATH;
import static org.opengoofy.congomall.bff.biz.common.SentinelLimitFlowConstant.ADD_CART_PATH;
import static org.opengoofy.congomall.bff.biz.common.SentinelLimitFlowConstant.CREATE_ORDER_PATH;

/**
 * 初始化限流配置
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
public class SentinelRuleConfig implements InitializingBean {
    
    @Value("${congomall.bff.qps-count:1}")
    private Integer qpsCount;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule createOrderRule = new FlowRule();
        createOrderRule.setResource(CREATE_ORDER_PATH);
        createOrderRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        createOrderRule.setCount(qpsCount);
        FlowRule addCartRule = new FlowRule();
        addCartRule.setResource(ADD_CART_PATH);
        addCartRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        addCartRule.setCount(qpsCount);
        FlowRule addAddressRule = new FlowRule();
        addAddressRule.setResource(ADD_ADDRESS_PATH);
        addAddressRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        addAddressRule.setCount(qpsCount);
        rules.add(createOrderRule);
        rules.add(addCartRule);
        rules.add(addAddressRule);
        FlowRuleManager.loadRules(rules);
    }
}
