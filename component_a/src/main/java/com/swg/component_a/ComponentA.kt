package com.swg.component_a

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent

/**
 *
 * @ProjectName:    CCDemo
 * @ClassName:      ComponentA
 * @Author:         Owen
 * @CreateDate:     2020/10/19 10:27
 * @UpdateUser:     更新者
 * @Description:     java类作用描述
 */
class ComponentA : IComponent {

    override fun getName(): String {
        //组件的名称，调用此组件的方式：
        // CC.obtainBuilder("ComponentA")...build().callAsync()
        return "demo.ComponentA"
    }

    /**
     * 组件被调用时的入口
     * 要确保每个逻辑分支都会调用到CC.sendCCResult，
     * 包括try-catch,if-else,switch-case-default,startActivity
     * @param cc 组件调用对象，可从此对象中获取相关信息
     * @return true:将异步调用CC.sendCCResult(...),用于异步实现相关功能，例如：文件加载、网络请求等
     *          false:会同步调用CC.sendCCResult(...),即在onCall方法return之前调用，否则将被视为不合法的实现
     */
    override fun onCall(cc: CC): Boolean {
        when (cc.actionName) {
            "showActivityA" -> openActivity(cc)
            "getLifecycleFragment" ->                 //demo for provide fragment object to other component
                getLifecycleFragment(cc)
            "lifecycleFragment.addText" -> lifecycleFragmentDoubleText(cc)
            "getInfo" -> getInfo(cc)
            else -> {
            }
        }
        return false
    }

    private fun lifecycleFragmentDoubleText(cc: CC) {
        val lifecycleFragment = cc.getParamItem<LifecycleFragment>("fragment")
        if (lifecycleFragment != null) {
            val text = cc.getParamItem("text", "")
            lifecycleFragment.addText(text)
            CC.sendCCResult(cc.callId, CCResult.success())
        } else {
            CC.sendCCResult(cc.callId, CCResult.error("no fragment params"))
        }
    }

    private fun getLifecycleFragment(cc: CC) {
        CC.sendCCResult(cc.callId, CCResult.successWithNoKey(LifecycleFragment()))
    }

    private fun getInfo(cc: CC) {
        val userName = "billy"
        CC.sendCCResult(cc.callId, CCResult.success("userName", userName))
    }

    private fun openActivity(cc: CC) {
        CCUtil.navigateTo(cc, ActivityA::class.java)
        CC.sendCCResult(cc.callId, CCResult.success())
    }

}