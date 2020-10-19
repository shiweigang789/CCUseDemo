package com.swg.component_a

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @ProjectName:    CCDemo
 * @ClassName:      ActivityA
 * @Author:         Owen
 * @CreateDate:     2020/10/19 9:53
 * @UpdateUser:     更新者
 * @Description:     java类作用描述
 */
class ActivityA: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.gravity = Gravity.CENTER
        textView.text = "ActivityA\nClick to finish this activity"
        setContentView(textView)
        textView.setOnClickListener { finish() }
    }

}