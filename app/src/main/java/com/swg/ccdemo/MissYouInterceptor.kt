package com.swg.ccdemo

import android.util.Log
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.Chain
import com.billy.cc.core.component.ICCInterceptor

/**
 *
 * @ProjectName:    CCDemo
 * @ClassName:      MissYouInterceptor
 * @Author:         Owen
 * @CreateDate:     2020/10/19 15:18
 * @UpdateUser:     更新者
 * @Description:    将返回结果中的字符串"billy"替换为"billy miss you"
 */
class MissYouInterceptor : ICCInterceptor {

    /**
     * 拦截器的拦截方法
     * 调用chain.proceed()方法让调用链继续向下执行
     * 在调用chain.proceed()方法之前，可以修改cc的参数
     * 在调用chain.proceed()方法之后，可以修改返回结果
     * @param chain 拦截器调用链
     * @return 调用结果对象
     */
    override fun intercept(chain: Chain?): CCResult {
        //获取调用链处理的cc对象

        //获取调用链处理的cc对象
        val cc = chain!!.cc
        val params = cc.params
        //可以在拦截器中修改params，此处只是仅仅用来打印一下
        //可以在拦截器中修改params，此处只是仅仅用来打印一下
        Log.i("MissYouInterceptor", "callId=" + cc.callId + ", params=" + params)
        //传递拦截器调用链
        // 不调用chain.proceed()方法, 可以中止调用链的继续传递（中止组件调用）
        // 譬如：埋点组件调用网络请求组件发送埋点信息
        //      1. 可以添加一个本地缓存拦截器：无网络时直接缓存本地数据库，不调用埋点组件；
        //      2. 埋点返回结果 ccResult.isSuccess()为false，也缓存到本地数据库
        //传递拦截器调用链
        // 不调用chain.proceed()方法, 可以中止调用链的继续传递（中止组件调用）
        // 譬如：埋点组件调用网络请求组件发送埋点信息
        //      1. 可以添加一个本地缓存拦截器：无网络时直接缓存本地数据库，不调用埋点组件；
        //      2. 埋点返回结果 ccResult.isSuccess()为false，也缓存到本地数据库
        val result = chain.proceed()
        //对返回的结果进行修改
        if (result.isSuccess) {
            val data = result.dataMap
            if (data != null) {
                for (key in data.keys) {
                    val value = data[key]
                    //将字符串中的"billy"替换为"billy miss you"
                    if (value != null && value is String) {
                        var str = value
                        str = str.replace("billy".toRegex(), "billy miss you")
                        data[key] = str
                    }
                }
            }
        }
        return result
    }
}