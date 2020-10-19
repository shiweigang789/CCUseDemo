package com.swg.component_a

/**
 *
 * @ProjectName:    CCDemo
 * @ClassName:      JsonFormat
 * @Author:         Owen
 * @CreateDate:     2020/10/19 9:57
 * @UpdateUser:     更新者
 * @Description:     java类作用描述
 */

object JsonFormat {

    /**
     * 默认每次缩进两个空格
     */
    const val EMPTY = "  "

    fun format(json: String): String {
        try {
            var empty = 0
            val charArray = json.toCharArray()
            val stringBuilder = StringBuilder()
            var i = 0
            while (i < charArray.size) {
                //若是双引号，则为字符串，下面语句会处理该字符串
                when (charArray[i]) {
                    '\"' -> {
                        stringBuilder.append(charArray[i])
                        i++
                        //查找字符串结束位置
                        while (i < charArray.size) {
                            //如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束

                            //如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
                            if (charArray[i] == '\"' && isDoubleSerialBackslash(charArray, i - 1)) {
                                stringBuilder.append(charArray[i])
                                i++
                                break
                            } else {
                                stringBuilder.append(charArray[i])
                                i++
                            }
                        }
                    }
                    ',' -> {
                        stringBuilder.append(',').append('\n').append(getEmpty(empty))
                        i++
                    }
                    '{', '[' -> {
                        empty++
                        stringBuilder.append(charArray[i]).append('\n').append(getEmpty(empty))
                        i++
                    }
                    '}', ']' -> {
                        empty--
                        stringBuilder.append('\n').append(getEmpty(empty)).append(charArray[i])
                        i++
                    }
                    else -> {
                        stringBuilder.append(charArray[i])
                        i++
                    }
                }
            }
            return stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return json
        }
    }

    private fun isDoubleSerialBackslash(chs: CharArray, i: Int): Boolean {
        var count = 0
        for (j in i downTo -1 + 1) {
            if (chs[j] == '\\') {
                count++
            } else {
                return count % 2 == 0
            }
        }
        return count % 2 == 0
    }

    /**
     * 缩进
     * @param count 缩进等级
     * @return 缩进的空格
     */
    private fun getEmpty(count: Int): String? {
        val stringBuilder = java.lang.StringBuilder()
        for (i in 0 until count) {
            stringBuilder.append(EMPTY)
        }
        return stringBuilder.toString()
    }


}

