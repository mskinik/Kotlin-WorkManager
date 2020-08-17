package com.mustafasuleymankinik.androidworkmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

const val C_OUTPUT_DATA="C-output"
class CWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val inputDataString=inputData.getString(C_INPUT_DATA).toString()

        
        return Result.success(outputData(inputDataString))
    }

    private fun outputData(inputDataString: String):Data
    {


        return Data.Builder().putString(C_OUTPUT_DATA,inputDataString).build()
    }

}