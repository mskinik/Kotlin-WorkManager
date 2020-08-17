package com.mustafasuleymankinik.androidworkmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

const val D_OUTPUT_DATA="D-output"
class DWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val inputDataString=inputData.getString(C_OUTPUT_DATA).toString()
        return Result.success(outputData(inputDataString))
    }

    private fun outputData(inputDataString:String):Data
    {

        return Data.Builder().putString(D_OUTPUT_DATA,"$inputDataString-D").build()
    }

}