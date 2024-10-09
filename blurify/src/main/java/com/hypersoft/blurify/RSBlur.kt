package com.hypersoft.blurify

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.RenderScript.RSMessageHandler
import android.renderscript.ScriptIntrinsicBlur

class RSBlur {

        fun blur(context: Context?, bitmap: Bitmap, radius: Int): Bitmap {
            var rs: RenderScript? = null
            var input: Allocation? = null
            var output: Allocation? = null
            var blur: ScriptIntrinsicBlur? = null
            try {
                rs = RenderScript.create(context)
                rs.messageHandler = RSMessageHandler()
                input = Allocation.createFromBitmap(
                    rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT
                )
                output = Allocation.createTyped(rs, input.type)
                blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

                blur.setInput(input)
                blur.setRadius(radius.toFloat())
                blur.forEach(output)
                output.copyTo(bitmap)
            } finally {
                if (rs != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        RenderScript.releaseAllContexts()
                    } else {
                        rs.destroy()
                    }
                }
                input?.destroy()
                output?.destroy()
                blur?.destroy()
            }

            return bitmap
        }
    }


