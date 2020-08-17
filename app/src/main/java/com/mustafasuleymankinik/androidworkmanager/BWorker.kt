package com.mustafasuleymankinik.androidworkmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

const val B_OUTPUT_DATA="B-output"
class BWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val inputDataString=inputData.getString(A_OUTPUT_DATA).toString()
        return Result.success(outputData(inputDataString))
    }
    private fun outputData(inputDataString:String):Data
    {

        return Data.Builder().putString(B_OUTPUT_DATA,"$inputDataString-B").build()
    }
}