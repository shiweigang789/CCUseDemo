package com.swg.ccdemo

import android.app.Application
import com.billy.cc.core.component.CC

/**
 *
 * @ProjectName:    CCDemo
 * @ClassName:      MyApp
 * @Author:         Owen
 * @CreateDate:     2020/10/16 17:48
 * @UpdateUser:     更新者
 * @Description:     java类作用描述
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CC.enableVerboseLog(BuildConfig.DEBUG)
        CC.enableDebug(true)
        CC.enableRemoteCC(true)
    }

}