package com.swg.ccdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponentCallback
import com.swg.component_a.JsonFormat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val COMPONENT_NAME_A = "demo.ComponentA"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initClick()
    }

    private fun initClick() {
        componentAOpenActivity.setOnClickListener {
            val cc = CC.obtainBuilder(COMPONENT_NAME_A)
                .setActionName("showActivityA")
                .build()
            val result = cc.call()
            showResult(cc, result)
        }
        componentAAsyncOpenActivity.setOnClickListener {
            CC.obtainBuilder(COMPONENT_NAME_A)
                .setActionName("showActivityA")
                .build().callAsyncCallbackOnMainThread(printResultCallback)
        }
        componentAGetData.setOnClickListener {
            val cc = CC.obtainBuilder(COMPONENT_NAME_A)
                .setActionName("getInfo")
                .build()
            val result = cc.call()
            showResult(cc, result)
        }
        componentAAsyncGetData.setOnClickListener {
            CC.obtainBuilder(COMPONENT_NAME_A)
                .setActionName("getInfo")
                .addInterceptor(MissYouInterceptor())
                .build().callAsyncCallbackOnMainThread(printResultCallback)
        }
    }

    private var printResultCallback = IComponentCallback { cc, result -> showResult(cc, result) }

    private fun showResult(cc: CC, result: CCResult) {
        val text = """
            result:${JsonFormat.format(result.toString())}
            \n\n---------------------\n\n
            cc:${JsonFormat.format(cc.toString())}
            """.trimIndent()
        console.text = text
    }

}